package agarioPrep.agarioGameObject

import agarioPrep.Physics.PhysicsVector

class Blob extends Position{
  val r = scala.util.Random

  this.amount = 0
  this.xyr: PhysicsVector = new PhysicsVector(r.nextInt(1500) - r.nextInt(1500), r.nextInt(1500) - r.nextInt(1500), 24)
}
