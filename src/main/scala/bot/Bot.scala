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

    def getPathTo(currentState: FieldInPlay, target: Field): Seq[MoveType] = {
        if (currentState.moveLeft.currentField == target) List(LEFT)
        else if (currentState.moveRight.currentField == target) List(RIGHT)
        else if (currentState.currentField == target || currentState.drop.currentField == target) List()
        else if (currentState.moveRight.moveRight.currentField == target) List(RIGHT,RIGHT)
        else if (currentState.moveLeft.moveLeft.currentField == target) List(LEFT,LEFT)
        else List(RIGHT, RIGHT, RIGHT, RIGHT, RIGHT)
    }
}
