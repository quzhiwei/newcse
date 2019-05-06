
package networking

import akka.actor.{Actor, ActorRef, Props}
import model._


class GameActor extends Actor {

  var players: Map[String, ActorRef] = Map()
  var food: List[ActorRef] = List()

  val game: Game = new Game()

  override def receive: Receive = {
    case message: AddPlayer => game.addPlayer(message.username)
    case message: RemovePlayer => game.removePlayer(message.username)
    case message: MovePlayer => game.players(message.username).move(new Vector(message.x, message.y))
    case message: StopPlayer => game.players(message.username).stop()

    case UpdateGame =>
      game.update()
    case SendGameState => sender() ! GameState(game.gameState())

    case message: Show => println(message.username)
  }
}

