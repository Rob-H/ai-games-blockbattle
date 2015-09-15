package field

import moves._
import scala.annotation.tailrec

class FieldInPlay(val field: Field, val shape: Shape, val locationOfShape: Location) {
    private def maybeAtLocation(potentialShape: Shape, potentialLocation: Location) = {
        if(potentialShape.canBePlacedIn(field, potentialLocation)) Some(new FieldInPlay(field, potentialShape, potentialLocation))
        else None
    }
    lazy val currentShapeType = shape.shapeType
    lazy val currentField = field.withShapeAt(shape, locationOfShape)

    lazy val moveRight = maybeAtLocation(shape, locationOfShape.right)
    lazy val moveLeft = maybeAtLocation(shape, locationOfShape.left)
    lazy val moveDown = maybeAtLocation(shape, locationOfShape.down)
    lazy val turnRight = maybeAtLocation(shape.turnRight, locationOfShape)
    lazy val turnLeft = maybeAtLocation(shape.turnLeft, locationOfShape)
    lazy val drop = {
        @tailrec def resultOfDrop(location: Location): Location = {
            val nextLocation = location.down
            if(!shape.canBePlacedIn(field, nextLocation)) location
            else resultOfDrop(nextLocation)
        }
        new FieldInPlay(field, shape, resultOfDrop(locationOfShape))
    }
    lazy val allValidMoves = {
        val allMoves = List(
            (LEFT, moveLeft),
            (RIGHT, moveRight),
            (DOWN, moveDown),
            (TURNRIGHT, turnRight),
            (TURNLEFT, turnLeft)
        )
        for( (action, Some(state)) <- allMoves ) yield (action, state)
    }

    def distanceFrom(other: FieldInPlay):Int =
        math.abs(locationOfShape.x - other.locationOfShape.x) +
        math.abs(locationOfShape.y - other.locationOfShape.y) +
        math.abs(((shape.degreesRotated / 90) - (other.shape.degreesRotated / 90)) % 2)

}
