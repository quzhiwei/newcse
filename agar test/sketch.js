

var blob;
var sfood
var sfoods = [];
var bfood
var bfoods = [];
var zoom = 1;
var spikes = [];
var spike

function setup() {
  createCanvas(1600, 800);
  blob = new Blob(0, 0, 24 );

  for (var i = 0; i < 5000; i++) {
    var x = random(-3000,3000);
    var y = random(-3000,3000);
    var size = random(2,6)
    sfoods[i] = new Small_Food(x, y, size);
  }
  for (var o = 0; o < 100; o++) {
    var x2 = random(-3000,3000);
    var y2 = random(-3000,3000);
    var size2 = random(12,30)
    bfoods[o] = new Big_Food(x2, y2, 12);
  }
    for (var u= 0; u<200;u++){
    var x1= random(-3000,3000);
    var y1= random(-3000,3000);
    var size1= random(32,64);
    spikes[u] = new Spike(x1,y1,size1);
  }

}




function draw() {
  background(120);

  translate(width/2, height/2);
  var newzoom = 64 / blob.r*1.2;
  zoom = lerp(zoom, newzoom, 0.01);
  scale(zoom);
  translate(-blob.pos.x, -blob.pos.y);

  for (var i = sfoods.length-1; i >=0; i--) {
    sfoods[i].show();
    if (blob.eats(sfoods[i])) {
      sfoods.splice(i, 1);
      // blob = new Blob(0, 0, 24);
    }
  }
  for (var o= bfoods.length-1; o >=0; o--) {
    bfoods[o].show();
    if (blob.eats(bfoods[o])) {
      bfoods.splice(o, 1);
      // bfood.
      // blob = new Blob(0, 0, 24);
    }
  }
  for (var u = spikes.length-1; u >=0; u--) {
    spikes[u].show();
    if (blob.eats(spikes[u])) {
      blob = new Blob(random(-3000,3000),random(-3000,3000), 24);
    }
  }


  blob.show();
  blob.update();

}
