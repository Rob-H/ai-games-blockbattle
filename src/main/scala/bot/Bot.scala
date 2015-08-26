package bot

import field._
import moves._

class Bot {
    val notRotated = 0;
    val rotatedOnce = 90;
    val rotatedTwice = 180;
    val rotatedThreeTimes = 270;

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndStates(field: Field, shapeType: ShapeType): Seq[Field] = {
        for {
            rotatedShape <- shapeType.uniqueRotations.map(Shape(shapeType, _))
            loc <- rotatedShape.allPossibleEndLocations(field)
        } yield field.withShapeAt(rotatedShape, loc)
    }
}
