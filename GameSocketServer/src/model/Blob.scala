package model

class Blob(inputLocation: Vector, inputVelocity: Vector) extends Object {

  val speed: Double = 4.0
  var size: Double = 10
  var velocity: Vector = new Vector(0, 0)

  def move(direction: Vector){
    val normalDirection = direction.normal2d()
    this.velocity = new Vector(normalDirection.x * speed, normalDirection.y * speed)
  }

  def stop(): Unit ={
    this.velocity = new Vector(0, 0)
  }
}
