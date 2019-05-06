var player;
var players = []
var sfood
var sfoods = [];
var bfood
var bfoods = [];
var zoom = 1;
var spikes = [];
var spike


function setup() {
  createCanvas(1600, 800);
  // socket = io.connect(localhost:3000)
  player = new Blob(random(-1500,1500), random(-1500,1500), 24 );
  players.push(player)

  for (var i = 0; i < 5000; i++) {
    var x = random(-2500,2500);
    var y = random(-2500,2500);
    var size = random(2,6)
    sfoods[i] = new Small_Food(x, y, size);
  }
  for (var o = 0; o < 1000; o++) {
    var x2 = random(-2500,2500);
    var y2 = random(-2500,2500);
    var size2 = random(6,9)
    bfoods[o] = new Big_Food(x2, y2, size2);
  }
    for (var u= 0; u<200;u++){
    var x1= random(-2500,2500);
    var y1= random(-2500,2500);
    var size1= random(24,64);
    spikes[u] = new Spike(x1,y1,size1);
  }

}




function draw() {
  background(255);

  translate(width/2, height/2);
  var newzoom = 64 / player.r*1.2;
  zoom = lerp(zoom, newzoom, 0.01);
  scale(zoom);
  translate(-player.pos.x, -player.pos.y);

  for (var i = sfoods.length-1; i >=0; i--) {
    sfoods[i].show();
    if (player.eats(sfoods[i])) {
      sfoods[i] = new Small_Food(random(-2500,2500), random(-2500,2500),random(2,6))
      // player = new Blob(0, 0, 24);
    }
  }
  for (var o= bfoods.length-1; o >=0; o--) {
    bfoods[o].show();
    if (player.eats(bfoods[o])) {
      // bfoods.splice(o, 1);
      // bfoods.push(new Big_Food(0,0,24))
      bfoods[o] = new Big_Food(random(-2500,2500), random(-2500,2500),random(6,9))
      // bfoods.push(new bfood(random(-100,100),random(-100,100),random(6,9)))
      // bfood.
      // player = new Blob(0, 0, 24);
    }
    // if(o<=500){
  //   for (var o <= 500; o < 1000; o++) {
  //   var x2 = random(-2500,2500);
  //   var y2 = random(-2500,2500);
  //   var size2 = random(6,9)
  //   bfoods[o] = new Big_Food(x2, y2, size2);
  // }
    // }
  }
  for (var u = spikes.length-1; u >=0; u--) {
    spikes[u].show();
    if (player.eats(spikes[u])) {
      player = new Blob(random(-1500,1500),random(-1500,1500), 24);
    }
  }
//   for (var p = players.length-1; p>= 0){
//     players[p].show();
//     players[p].update();
// }

  player.show();
  player.update();

}
