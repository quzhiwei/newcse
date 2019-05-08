package model

import play.api.libs.json.{JsValue, Json}
import scala.util.Random


class Game {

  var world: List[Object] = List()
  var players: Map[String, Blob] = Map()
  var food_b: List[Food_b] = List()
  var food_s: List[Food_s] = List()
  val playerSize: Double = 10

  var lastUpdateTime: Long = System.nanoTime()


  def initialize(): Unit = {
    food_b.foreach(po => po.destroy())
    food_s.foreach(po => po.destroy())
    food_b = List()
    food_s = List()
  }


  def addPlayer(id: String): Unit = {
    val player = new Blob(new Vector(Random.nextDouble() * 3000 - 1500, Random.nextDouble() * 3000 - 1500), new Vector(0, 0))
    players += (id -> player)
    world = player :: world
  }


  def removePlayer(id: String): Unit = {
    players(id).destroy()
    players -= id
  }


  def placeFoodB(amount: Int): Unit = {
    for (i <- 0 to amount) {
      val big_food = new Food_b
      food_b = big_food :: food_b
      world = big_food :: world
    }
  }


  def placeFoodS(amount: Int): Unit = {
    for (i <- 0 to amount) {
      val small_food = new Food_b
      food_b = small_food :: food_b
      world = small_food :: world
    }
  }


  def update(): Unit = {
    val time: Long = System.nanoTime()
    val dt = (time - this.lastUpdateTime) / 1000000000.0
    checkForPlayerHits()
    this.lastUpdateTime = time
  }

  def gameState(): String = {
    val gameState: Map[String, JsValue] = Map(
      "players" -> Json.toJson(this.players.map({ case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.location.x),
        "y" -> Json.toJson(v.location.y),
        "v_x" -> Json.toJson(v.Velocity.x),
        "v_y" -> Json.toJson(v.Velocity.y),
        "size" -> Json.toJson(v.size),
        "id" -> Json.toJson(k))) })),
      "food" -> Json.toJson(this.food_s.map({ po => Json.toJson(Map("x" -> po.location.x, "y" -> po.location.y)) }))
    )

    Json.stringify(Json.toJson(gameState))
  }


  def distance (obj1: Object, obj2: Object): Double = {
    val x1 = obj1.location.x
    val y1 = obj1.location.y
    val x2 = obj2.location.x
    val y2 = obj2.location.y
    math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))
  }

  def checkForPlayerHits(): Unit = {
    for (player <- players.values) {
      for (food <- food_b) {
        val dis = distance(player, food)
        if (dis <= player.size + food.size) {
          food.destroy()
          player.size += food.size
        }
      }
    }
  }


}

