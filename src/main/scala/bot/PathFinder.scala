package bot

import field._
import moves._
import scala.annotation.tailrec

trait PathFinder {
    def getPathTo(startState: FieldInPlay, target: FieldInPlay, quick: Boolean = false): Option[Seq[MoveType]] = {

        type PathAndState = Tuple2[List[MoveType], FieldInPlay]

        def getAllActionsForState(state: FieldInPlay, currentPath: List[MoveType]) =
            state.allValidMoves.map { case (move, state) => (move :: currentPath, state)}

        @tailrec def iter(currentStateAndPath: PathAndState, statesToExplore: List[PathAndState], explored: Set[Field]): Option[Seq[MoveType]] = {
            val (currentPath, currentState) = currentStateAndPath
            val weAreFinished =
                currentState.currentField == target.currentField ||
                ((quick || currentState.currentShapeType != T) && currentState.drop.currentField == target.currentField)

            if (weAreFinished) Some(currentPath.reverse)
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
