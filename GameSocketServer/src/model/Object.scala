package model

class Object(var location: Vector) {

  var destroyed: Boolean = false

  def destroy(): Unit = {
    destroyed = true
  }

  def collide(): Unit = {}
}

