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

    def getPathTo(currentState: FieldInPlay, target: Field): Seq[MoveType] = List()
}
