package bot

import field._
import moves._

class Bot extends PathFinder{

    val testStartLocation = Location(3, -1)
    val maxTimeout = 10000
    val halfMaxTimeout = maxTimeout / 2
    def getMoves(state: BotState, timeout: Long): Seq[MoveType] = {
        val startState = new FieldInPlay(state.getMyField, Shape(state.getCurrentShape), state.getShapeLocation)

        (for {
            nextIdealEndState <- getOrderedPossibleEndStates(state.getMyField, state.getCurrentShape, state.getNextShape, timeout)
            path <- getPathTo(startState, nextIdealEndState)
        } yield path) match {
            case head #:: _ => head
            case _ => Nil
        }
    }

    def getOrderedPossibleEndStates(field: Field, currentShape: ShapeType, nextShape: ShapeType, timeout: Long = maxTimeout): Stream[FieldInPlay] = {
        def getHeuristicOfNextShape(currentState: Field) = {
            val startOfPath = new FieldInPlay(currentState.finalise, Shape(nextShape), testStartLocation)
            currentState.finalise.getPossibleEndStates(nextShape).sortBy(-_.field.heuristic).take(5)
                .filter { state => timeout < halfMaxTimeout || getPathTo(startOfPath, state, true).nonEmpty }
                .foldLeft(-(field.height*field.width)) ((current, next) => math.max(current, currentState.heuristic + (next.field.heuristic*2)))
        }

        val bestEndStatesForCurrentShape = field.getPossibleEndStates(currentShape).sortBy(-_.field.heuristic).toStream.take(5)
        bestEndStatesForCurrentShape sortBy { state => -getHeuristicOfNextShape(state.field) }
    }
}

