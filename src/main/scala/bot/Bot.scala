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
        type PathAndState = Tuple2[List[MoveType], FieldInPlay]
        def getAllActionsForState(state: FieldInPlay, currentPath: List[MoveType]) = {
            val allMoves = List(
                (LEFT :: currentPath, state.moveLeft),
                (RIGHT :: currentPath, state.moveRight),
                (DOWN :: currentPath, state.moveDown),
                (TURNRIGHT :: currentPath, state.turnRight),
                (TURNLEFT :: currentPath, state.turnLeft)
            )
            val allValidMoves = for( (path, Some(state)) <- allMoves ) yield (path, state)
            allValidMoves
        }
        def iter(currentStateAndPath: PathAndState, statesToExplore: List[PathAndState], explored: Set[Field]): Option[Seq[MoveType]] = {
            val (currentPath, currentState) = currentStateAndPath
            if (currentState.currentField == target || currentState.drop.currentField == target) Some(currentPath.reverse)
            else {
                statesToExplore match {
                    case (path, state) :: remainingStates => {
                        if(explored contains state.currentField) iter(currentStateAndPath, remainingStates, explored)
                        else iter((path, state), remainingStates ++ getAllActionsForState(state, path), explored + currentState.currentField)
                    }
                    case _ => None
                }
            }
        }
        iter((Nil, startState), getAllActionsForState(startState, Nil), Set())
    }
}
