

var blob;
var food
var foods = [];
var zoom = 1;
var spikes = [];
var spike

function setup() {
  createCanvas(1600, 800);
  blob = new Blob(0, 0, 24
    );
  for (var i = 0; i < 3000; i++) {
    var x = random(-3000,3000);
    var y = random(-3000,3000);
    var size = random(2,6)
    foods[i] = new Food(x, y, size);
  }
    for (var u= 0; u<200;u++){
    var x1= random(-3000,3000);
    var y1= random(-3000,3000);
    var size1= random(32,64);
    spikes[u] = new Spike(x1,y1,size1);
  }

}

function draw() {
  background(50);

  translate(width/2, height/2);
  var newzoom = 64 / blob.r*1.2;
  zoom = lerp(zoom, newzoom, 0.01);
  scale(zoom);
  translate(-blob.pos.x, -blob.pos.y);

  for (var i = foods.length-1; i >=0; i--) {
    foods[i].show();
    if (blob.eats(foods[i])) {
      foods.splice(i, 1);
    }
  }
  for (var u = spikes.length-1; u >=0; u--) {
    spikes[u].show();
    // if (spike.eats(blob)) {
    //   blob.x = random(-3000,3000)
    //     blob.y = random(-3000,3000)
    //     blob.r = random(24)
    // }
  }


  blob.show();
  blob.update();

}
