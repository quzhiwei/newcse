

function Blob(x, y, r) {
  this.pos = createVector(x, y);
  this.r = r;
  this.vel = createVector(0, 0);

  this.update = function() {
    var newvel = createVector(mouseX - width / 2, mouseY - height / 2);
    newvel.div(50);
    //newvel.setMag(3);
    newvel.limit(3);
    this.vel.lerp(newvel, 0.2);
    this.pos.add(this.vel);
  }
  
  this.eats = function(other) {
    var d = p5.Vector.dist(this.pos, other.pos);
    if (d < this.r + other.r) {
      if(this.r > other.r){
        var sum = PI * this.r * this.r + PI * other.r * other.r;
      this.r = sqrt(sum / PI);
      }
      if(this.r < other.r){
        var sum = PI * this.r * this.r + PI * other.r * other.r;
      other.r = sqrt(sum / PI);
      }
      // var sum = PI * this.r * this.r + PI * other.r * other.r;
      // this.r = sqrt(sum / PI);
      //this.r += other.r;
      return true;
    } else {
      return false;
    }
  }

  this.constrain = function() {
    blob.pos.x = constrain(blob.pos.x, -width / 4, width / 4);
    blob.pos.y = constrain(blob.pos.y, -height / 4, height / 4);
  }

  this.show = function() {
    fill(255);
    ellipse(this.pos.x, this.pos.y, this.r * 2, this.r * 2);
  }
}


function Small_Food(x, y, r) {
  this.pos = createVector(x, y);
  this.r = r;
  this.vel = createVector(0, 0);
  this.show = function() {
    fill(random(100,200),random(100,200),random(100,200));
    ellipse(this.pos.x, this.pos.y, this.r * 2, this.r * 2);
  }
}

function Big_Food(x, y, r) {
  this.pos = createVector(x, y);
  this.r = r;
  this.vel = createVector(0, 0);
  this.show = function() {
    fill(random(100,200),random(100,200),random(100,200));
    ellipse(this.pos.x, this.pos.y, this.r * 2, this.r * 2);
  }
}

function Spike(x, y, r) {
  this.pos = createVector(x, y);
  this.r = r;
  this.vel = createVector(0, 0);
  this.show = function() {
    fill(255,0,0);
    ellipse(this.pos.x, this.pos.y, this.r * 2, this.r * 2);
  }
    // this.destory = function(other){
    //  var d2 = p5.Vector.dist(this.pos, other.pos);
    //   if (d2 < this.r + other.r) {
    //     other.x = random(-3000,3000)
    //     other.y = random(-3000,3000)
    //     other.r = random(24)
    //     return true
    //   }
    //   else{
    //     return false
    //   }
    // }
      this.eats = function(other) {
    var d = p5.Vector.dist(this.pos, other.pos);
    if (d < this.r + other.r) {
      if(this.r > other.r){
        var sum = PI * this.r * this.r + PI * other.r * other.r;
      this.r = sqrt(sum / PI);
      }
      if(this.r < other.r){
        var sum = PI * this.r * this.r + PI * other.r * other.r;
      other.r = sqrt(sum / PI);
      }
      // var sum = PI * this.r * this.r + PI * other.r * other.r;
      // this.r = sqrt(sum / PI);
      //this.r += other.r;
      return true;
    } else {
      return false;
    }
  }
}