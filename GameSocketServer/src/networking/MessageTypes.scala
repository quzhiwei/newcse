package networking

// Received by Multiple Types
case object SendGameState
case class GameState(gameState: String)


// Received by GameActor
case object UpdateGame
case object TowersFire
case class AddPlayer(username: String)
case class RemovePlayer(username: String)
case class MovePlayer(username: String, x: Double, y:Double)
case class StopPlayer(username: String)
case class Show(username: String)
