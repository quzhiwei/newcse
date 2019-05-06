
class BigFood extends Position{
  val r = scala.util.Random

  this.amount = 1000
  this.x = r.nextInt(2500) - r.nextInt(2500)
  this.y = r.nextInt(2500) - r.nextInt(2500)
  this.r = r.nextInt(9 - 6) + 6
}
