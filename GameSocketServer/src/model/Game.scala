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
    val player = new Blob(startingVector(), new Vector(0, 0))
    players += (id -> player)
    world = player :: world
  }


  def removePlayer(id: String): Unit = {
    players(id).destroy()
    players -= id
  }


  def placeFoodB(x: Int, y: Int): Unit = {
    val big_food = new Food_b(x, y)
    food_b = big_food :: food_b
    world = big_food :: world
  }


  def placeFoodS(x: Int, y: Int): Unit = {
    val small_food = new Food_b(x, y)
    food_b = small_food :: food_b
    world = small_food :: world
  }


  def placeFoodS(x: Int, y: Int): Unit = {
    food_s = new Food_s(x, y) :: food_s
  }


  def startingVector(): Vector = {
    val x = Random.nextInt(5000) - 2500
    val y = Random.nextInt(5000) - 2500
    new Vector(level.startingLocation.x + 0.5, level.startingLocation.y + 0.5)
  }


  def update(): Unit = {
    val time: Long = System.nanoTime()
    val dt = (time - this.lastUpdateTime) / 1000000000.0
    Physics.updateWorld(this.world, dt)
    checkForPlayerHits()
    projectiles = projectiles.filter(po => !po.destroyed)
    this.lastUpdateTime = time
  }

  def gameState(): String = {
    val gameState: Map[String, JsValue] = Map(
      "walls" -> Json.toJson(this.walls.map({ w => Json.toJson(Map("x" -> w.x, "y" -> w.y)) })),
      "towers" -> Json.toJson(this.towers.map({ t => Json.toJson(Map("x" -> t.x, "y" -> t.y)) })),
      "players" -> Json.toJson(this.players.map({ case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.location.x),
        "y" -> Json.toJson(v.location.y),
        "v_x" -> Json.toJson(v.velocity.x),
        "v_y" -> Json.toJson(v.velocity.y),
        "id" -> Json.toJson(k))) })),
      "projectiles" -> Json.toJson(this.projectiles.map({ po => Json.toJson(Map("x" -> po.location.x, "y" -> po.location.y)) }))
    )

    Json.stringify(Json.toJson(gameState))
  }


  def checkForPlayerHits(): Unit = {
    // TODO: Objective 3
    for (player <- players.values) {
      for (projectile <- projectiles) {
        val distance = player.location.distance2d(projectile.location)
        if (distance <= playerSize) {
          projectile.destroy()
          player.location = startingVector()
        }
      }
    }
  }


}

