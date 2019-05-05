import json
import socket
from threading import Thread

from flask import Flask, jsonify, render_template, send_from_directory, request, make_response, redirect
from flask_socketio import SocketIO

import gameSession
import dbconn

import eventlet
eventlet.monkey_patch()

app = Flask(__name__)
socket_server = SocketIO(app)

usernameToSid = {}

# ** Connect to Scala TCP socket server **

#scala_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#scala_socket.connect(('localhost', 8000))
delimiter = "~"


def listen_to_scala(the_socket):
    buffer = ""
    while True:
        buffer += the_socket.recv(1024).decode()
        while delimiter in buffer:
            message = buffer[:buffer.find(delimiter)]
            buffer = buffer[buffer.find(delimiter) + 1:]
            get_from_scala(message)


def get_from_scala(data):
    message = json.loads(data)
    username = message["username"]
    user_socket = usernameToSid.get(username, None)
    if user_socket:
        socket_server.emit('message', data, room=user_socket)


#def send_to_scala(data):
#    scala_socket.sendall((json.dumps(data) + delimiter).encode())


#
#Thread(target=listen_to_scala, args=(scala_socket,)).start()
#


@app.route('/')
def index():
    return render_template('index.html')


@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify({'error': 'Page not found'}), 404)


@app.route('/players', methods=['GET'])
def online_users():
    showList = json.dumps(session.show_all_players(), ensure_ascii=False)
    return showList


@app.route('/server/join/<string:username>', methods=['GET', 'POST'])
def join_session(username):
    session.add_player(username)
    flow = {"username": username}
    op_db.insert_data('online_players', flow)
    return redirect('/players')


@app.route('/server/leave/<string:username>', methods=['GET', 'POST'])
def leave_session(username):
    session.remove_player(username)
    flow = {"username": username}
    op_db.delete_data('online_players', flow)
    return redirect('/players')


if __name__ == '__main__':
    session = gameSession.session()
    op_db = dbconn.mongo()
    app.run()
