package model

class Food_s(x: Int, y: Int) extends Object {

  var size: Double = 2

  override def collide(): Unit = {
    this.destroy()
  }
}

