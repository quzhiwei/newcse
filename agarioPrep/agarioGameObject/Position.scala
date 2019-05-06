package agarioPrep.agarioGameObject

import play.api.libs.json.{JsValue, Json}
import agarioPrep.Physics.PhysicsVector

abstract class Position{
  var amount: Int = 0
  var xyr: PhysicsVector = new PhysicsVector(0, 0, 0)

  def toJsValue(): JsValue = {
    Json.toJson(Map("amount" -> Json.toJson(this.amount), "xyr" -> Json.toJson(this.xyr)))
  }
}