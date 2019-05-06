package networking

import java.net.InetSocketAddress
import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString
import play.api.libs.json.{JsValue, Json}
import java.net.InetSocketAddress

case object UpdateGames

case object AutoSave

case class GameState(gameState: String)

class GameServer extends Actor {

  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 8000))
  var clients: Set[ActorRef] = Set()
  var gameSessions: Map[String, ActorRef] = Map()


  def generate_objects(): Unit = {}


  override def receive: Receive = {

    //    Example of adding an actor with this actor as its supervisor
    //    Note that we use the context of this actor and do not create a new actor system
    //    val childActor = context.actorOf(Props(classOf[GameActor], username))

    case b: Bound => println("Listening on port: " + b.localAddress.getPort)

    case c: Connected =>
      println("Client Connected: " + c.remoteAddress)
      this.clients = this.clients + sender()
      sender() ! Register(self)

    case PeerClosed =>
      println("Client Disconnected: " + sender())
      this.clients = this.clients - sender()

    case r: Received =>
      println("Received: " + r.data.utf8String)
      val parsed: JsValue = Json.parse(r.data.utf8String)
      val name = (parsed \ "username").as[String]
      val action = (parsed \ "action").as[String]

      if (action == "connected") {
        val session = context.actorOf(Props(classOf[GameActor], name))
        session ! Setup
        this.gameSessions = this.gameSessions + (name -> session) // sender() or session?
      }
      if (action == "disconnected") {
        val session = gameSessions(name)
        this.gameSessions = this.gameSessions - name
        session ! PoisonPill
      }

    case UpdateGames => this.gameSessions.values.foreach((client: ActorRef) => client !  Update)

    case AutoSave => this.gameSessions.values.foreach((client: ActorRef) => client !  Save)

    case gs: GameState =>
      val delimiter = "~"
      val msg: String = gs.gameState + delimiter
      this.clients.foreach((client: ActorRef) => client !  Write(ByteString(msg)))
  }
}


object ClickerServer {

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem()

    import actorSystem.dispatcher
    import scala.concurrent.duration._

    val server = actorSystem.actorOf(Props(classOf[GameServer]))

    actorSystem.scheduler.schedule(0 milliseconds, 100 milliseconds, server, UpdateGames)
    actorSystem.scheduler.schedule(0 milliseconds, 5000 milliseconds, server, AutoSave)
  }
}
