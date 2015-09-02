package bot

import field._
import moves._

class Bot {
    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
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
                    (TURNRIGHT, currentState.turnRight)
                )

                val solutions = for {
                    (action, Some(state)) <- nextSteps
                    if(!(explored contains state.currentField))
                    solution <- iter(state, explored + currentState.currentField)
                } yield action :: solution.toList

                solutions match {
                    case head #:: _ => Some(head)
                    case _ => None
                }
            }
        }
        iter(startState, Set())
    }
}
