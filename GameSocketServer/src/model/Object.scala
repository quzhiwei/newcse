package model

class Object {

  var destroyed: Boolean = false

  def destroy(): Unit = {
    destroyed = true
  }

  def collide(): Unit = {}
}

