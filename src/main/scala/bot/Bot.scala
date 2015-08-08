package bot

import moves._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Bot {

  def getMoves(state: BotState, timeout: Long): List[MoveType] = {
    val res = new ArrayBuffer[MoveType]()

    val allMoves = Array(DOWN, LEFT, RIGHT, TURNLEFT, TURNRIGHT)

    res.toList
  }
}
