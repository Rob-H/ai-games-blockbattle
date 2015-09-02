package bot

import field._
import moves._

class Bot {
    def getMoves(state: BotState, timeout: Long): Seq[MoveType] = {
        val startState = new FieldInPlay(state.getMyField, Shape(state.getCurrentShape), state.getShapeLocation)
        val possibleEndStates = (getPossibleEndStates(state.getMyField, state.getCurrentShape) sortBy (-_.heuristic)).toStream

        (for {
            nextIdealEndState <- possibleEndStates
            path <- getPathTo(startState, nextIdealEndState)
        } yield path) match {
            case head #:: _ => head
            case _ => Nil
        }
    }

    def getPossibleEndStates(field: Field, shapeType: ShapeType): Seq[Field] = {
        for {
            rotatedShape <- shapeType.uniqueRotations.map(Shape(shapeType, _))
            loc <- rotatedShape.allPossibleEndLocations(field)
        } yield field.withShapeAt(rotatedShape, loc)
    }

    def getPathTo(startState: FieldInPlay, target: Field): Option[Seq[MoveType]] = {
        def iter(currentState: FieldInPlay, explored: Set[Field]): Option[Seq[MoveType]] = {
            if (currentState.currentField == target || currentState.drop.currentField == target) Some(List())
            else {
                val nextSteps = Stream(
                    (LEFT, currentState.moveLeft),
                    (RIGHT, currentState.moveRight),
                    (DOWN, currentState.moveDown),
                    (TURNRIGHT, currentState.turnRight),
                    (TURNLEFT, currentState.turnLeft)
                )

                val solutions = for {
                    (action, Some(state)) <- nextSteps
                    if(!(explored contains state.currentField))
                    restOfSolution <- iter(state, explored + currentState.currentField)
                } yield action :: restOfSolution.toList

                solutions match {
                    case head #:: _ => Some(head)
                    case _ => None
                }
            }
        }
        iter(startState, Set())
    }
}
