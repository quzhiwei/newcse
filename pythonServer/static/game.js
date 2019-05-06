var username = "";

var socket = io.connect({transports: ['websocket']});
socket.on('gameState', parseGameState);


//var canvas = document.getElementById("canvas");
//var context = canvas.getContext("2d");
//context.globalCompositeOperation = 'source-over';


function parseGameState(event) {
    // console.log(event);
    const gameState = JSON.parse(event);

    
    for (let player of gameState['players']) {
        placeCircle(player['x'], player['y'], player['size']);
    }
}


function setup() {
    createCanvas(1000, 800);
    background(255);

}


function draw() {
    background(255);
}


function placeCircle(x, y, size) {
    fill(61,226,245);
    ellipse(x, y, size, size);
}

