package agarioPrep.agarioGameObject

import agarioPrep.Physics.PhysicsVector

class Spike extends Position{
  val r = scala.util.Random

  this.amount = 200
  this.xyr: PhysicsVector = new PhysicsVector(r.nextInt(2500) - r.nextInt(2500), r.nextInt(2500) - r.nextInt(2500), r.nextInt(64 - 24) + 24)
}