import json
import socket
from threading import Thread
from datetime import timedelta

from flask import Flask, jsonify, render_template, send_from_directory, request, make_response
from flask_socketio import SocketIO

import dbconn

import eventlet
eventlet.monkey_patch()

app = Flask(__name__)
app.config['DEBUG'] = True
app.config['SEND_FILE_MAX_AGE_DEFAULT'] = timedelta(seconds = 5)
socket_server = SocketIO(app)

scala_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
scala_socket.connect(('localhost', 8000))
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
    socket_server.emit('gameState', data, broadcast=True)


def send_to_scala(data):
    scala_socket.sendall((json.dumps(data) + delimiter).encode())


Thread(target=listen_to_scala, args=(scala_socket,)).start()


@app.route('/')
def index():
    return render_template('index.html')


@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify({'error': 'Page not found'}), 404)


@socket_server.on('connect')
def got_message():
    print(request.sid + " connected")
    message = {"username": request.sid, "action": "connected"}
    send_to_scala(message)


@socket_server.on('disconnect')
def disconnect():
    print(request.sid + " disconnected")
    message = {"username": request.sid, "action": "disconnected"}
    send_to_scala(message)


@socket_server.on('keyStates')
def key_state(jsonKeyStates):
    key_states = json.loads(jsonKeyStates)
    x = 0.0
    if key_states["a"] and not key_states["d"]:
        x = -1.0
    elif not key_states["a"] and key_states["d"]:
        x = 1.0
    y = 0.0
    if key_states["w"] and not key_states["s"]:
        y = -1.0
    elif not key_states["w"] and key_states["s"]:
        y = 1.0
    message = {"username": request.sid, "action": "move", "x": x, "y": y}
    send_to_scala(message)


@app.route('/<path:filename>')
def static_files(filename):
    return send_from_directory('static', filename)


players = []


@app.route('/<string:username>', methods=['GET', 'POST'])
def pass_message(username):
    players.append(username)
    message = {"username": username, "action": "show"}
    send_to_scala(message)
    showlist = json.dumps(players, ensure_ascii=False)
    return showlist


# @app.route('/game', methods=["POST", "GET"])
# def game():
#     if request.method == "POST":
#         username = request.form.get('username')
#     else:
#         username = "guest" + str(randint(0, 100000))
#     return render_template('game.html', username=username)


# @app.route('/players', methods=['GET'])
# def online_users():
#     showList = json.dumps(session.show_all_players(), ensure_ascii=False)
#     return showList


if __name__ == '__main__':
    op_db = dbconn.mongo()
    socket_server.run(app, port=8080)
    #app.run()
