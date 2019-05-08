var username = "";

var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);

//var x = 0
//var y = 0
//var size =0


//var canvas = document.getElementById("canvas");
//var context = canvas.getContext("2d");
//context.globalCompositeOperation = 'source-over';


function parseGameState(event) {
    // console.log(event);
    const gameState = JSON.parse(event);

    //x = gameState['players'][player]['x']
    for (let player of gameState['players']) {
        placeCircle(player['x'], player['y'], player['size']);
    }
}


function setup() {
    createCanvas(1000, 800);
    background(255);

}


function draw(x, y, size) {
    background(255);
    fill(61,226,245);
    ellipse(x, y, size, size);
}


function placeCircle(x, y, size) {
    draw(x, y, size)

}

