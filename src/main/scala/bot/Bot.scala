package bot

import field._
import moves._

class Bot extends PathFinder{

    val testStartLocation = Location(3, -1)

    def getMoves(state: BotState, timeout: Long): Seq[MoveType] = {
        val startState = new FieldInPlay(state.getMyField, Shape(state.getCurrentShape), state.getShapeLocation)

        (for {
            nextIdealEndState <- getOrderedPossibleEndStates(state.getMyField, state.getCurrentShape, state.getNextShape)
            path <- getPathTo(startState, nextIdealEndState)
        } yield path) match {
            case head #:: _ => head
            case _ => Nil
        }
    }

    def getOrderedPossibleEndStates(field: Field, currentShape: ShapeType, nextShape: ShapeType): Stream[Field] = {
        def getHeuristicOfNextShape(currentState: Field) = {
            val startOfPath = new FieldInPlay(currentState.finalise, Shape(nextShape), testStartLocation)
            currentState.finalise.getPossibleEndStates(nextShape).sortBy(-_.heuristic).take(5)
                .filter(getPathTo(startOfPath, _).nonEmpty)
                .foldLeft(-(field.height*field.width)) ((current, next) => math.max(current, currentState.heuristic + (next.heuristic*2)))
        }

        val bestEndStatesForCurrentShape = field.getPossibleEndStates(currentShape).sortBy(-_.heuristic).toStream.take(5)
        bestEndStatesForCurrentShape sortBy (-getHeuristicOfNextShape(_))
    }
}

