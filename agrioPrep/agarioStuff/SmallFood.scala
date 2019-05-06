import scala.reflect.api.Position

class SmallFood extends Position{
  val r = scala.util.Random
  this.x = r.nextInt(2500) - r.nextInt(2500)
  this.y = r.nextInt(2500) - r.nextInt(2500)
  this.r = r.nextInt(6-2) + 2
}
