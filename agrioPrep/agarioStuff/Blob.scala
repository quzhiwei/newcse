
class Blob extends Position{
  val r = scala.util.Random
  this.x = r.nextInt(1500) - r.nextInt(1500)
  this.y = r.nextInt(1500) - r.nextInt(1500)
  this.r = 24
}
