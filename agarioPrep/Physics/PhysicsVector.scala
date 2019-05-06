package agarioPrep.Physics


class PhysicsVector(var x: Double, var y: Double, var r: Double = 0.0){
  override def toString: String = {
    "(" + x + ", " + y + ", " + r + ")"
  }
}
