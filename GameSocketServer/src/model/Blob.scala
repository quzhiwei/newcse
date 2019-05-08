package model

class Blob(var Location: Vector, var Velocity: Vector) extends Object(Location) {

  val speed: Double = 4.0
  var size: Double = 10

  def move(direction: Vector){
    val normalDirection = direction.normal2d()
    this.Velocity = new Vector(normalDirection.x * speed, normalDirection.y * speed)
  }

  def stop(): Unit ={
    this.Velocity = new Vector(0, 0)
  }
}
