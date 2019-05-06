package agarioPrep.agarioGameObject

import agarioPrep.Physics.PhysicsVector

class BigFood extends Position{
  val r = scala.util.Random

  this.amount = 1000
  this.xyr: PhysicsVector = new PhysicsVector(r.nextInt(2500) - r.nextInt(2500), r.nextInt(2500) - r.nextInt(2500), r.nextInt(9 - 6) + 6)
}
