package bot

import field._
import moves._

class Bot extends PathFinder{

    def getMoves(state: BotState, timeout: Long): Seq[MoveType] = {
        val startState = new FieldInPlay(state.getMyField, Shape(state.getCurrentShape), state.getShapeLocation)
        val possibleEndStates = (state.getMyField.getPossibleEndStates(state.getCurrentShape) sortBy (-_.heuristic)).toStream

        (for {
            nextIdealEndState <- possibleEndStates
            path <- getPathTo(startState, nextIdealEndState)
        } yield path) match {
            case head #:: _ => head
            case _ => Nil
        }
    }
}

