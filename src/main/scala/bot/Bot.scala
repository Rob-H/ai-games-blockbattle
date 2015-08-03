package bot

import moves._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * User: goodg_000
 * Date: 12.07.2015
 * Time: 2:23
 */
class Bot {

  def getMoves(state: BotState, timeout: Long): List[MoveType] = {
    val res = new ArrayBuffer[MoveType]()

    val allMoves = Array(DOWN, LEFT, RIGHT, TURNLEFT, TURNRIGHT)
    val rnd = new Random()

    val numberOfMoves = rnd.nextInt(41)

    for (i <- 0 to numberOfMoves) {
      res += allMoves(rnd.nextInt(allMoves.length))
    }

    res.toList
  }
}
