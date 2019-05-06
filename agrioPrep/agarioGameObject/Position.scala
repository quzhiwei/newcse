import play.api.libs.json.{JsValue, Json}

abstract class Position{
  var amount: Int = 0
  var x: Int = 0
  var y: Int = 0
  var r: Int = 0

  def toJsValue(): JsValue = {
    Json.toJson(Map("amount" -> Json.toJson(this.amount), "x" -> Json.toJson(this.x), "y" -> Json.toJson(this.y),
      "r" -> Json.toJson(this.r)))
  }
}