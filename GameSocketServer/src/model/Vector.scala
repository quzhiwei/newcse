package model

class Vector(var x: Double, var y: Double) {

  def distance2d(other: Vector): Double = {
    Math.sqrt(Math.pow(x - other.x, 2.0) + Math.pow(y - other.y, 2.0))
  }

  def normal2d(): Vector = {
    if (x == 0 && y == 0) {
      new Vector(0, 0)
    } else {
      val magnitude = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0))
      new Vector(x / magnitude, y / magnitude)
    }
  }
}

