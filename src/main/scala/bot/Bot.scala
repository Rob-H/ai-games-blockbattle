package bot

import field._
import moves._

class Bot extends PathFinder{

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
        def getHeuristicOfNextShape(endState: Field) =
            endState.finalise.getPossibleEndStates(nextShape)
                .foldLeft(-(field.height*field.width))((current, next) => math.max(current, endState.heuristic + (next.heuristic*2)))

        val bestEndStatesForCurrentShape = (field.getPossibleEndStates(currentShape) sortBy (-_.heuristic)).toStream.take(5)
        bestEndStatesForCurrentShape sortBy (-getHeuristicOfNextShape(_))
    }
}

