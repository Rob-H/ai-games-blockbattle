package moves

case class MoveType(move: String) {}

object DOWN extends MoveType("down")
object LEFT extends MoveType("left")
object RIGHT extends MoveType("right")
object TURNLEFT extends MoveType("turnleft")
object TURNRIGHT extends MoveType("turnright")
