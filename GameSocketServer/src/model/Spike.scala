package model

import scala.util.Random

class Spike extends Object(new Vector(Random.nextDouble() * 5000 - 2500, Random.nextDouble() * 3000 - 2500)) {

  //var size: Double = 0

  override def collide(): Unit = {
    this.destroy()
  }
}
