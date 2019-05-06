package agarioPrep.agarioGameObject

import agarioPrep.Physics.PhysicsVector

class SmallFood extends Position{
  val r = scala.util.Random

  this.amount = 5000
  this.xyr: PhysicsVector = new PhysicsVector(r.nextInt(2500) - r.nextInt(2500), r.nextInt(2500) - r.nextInt(2500), r.nextInt(6-2) + 2)
}
