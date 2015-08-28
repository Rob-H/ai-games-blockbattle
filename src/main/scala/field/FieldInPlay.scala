package field

import scala.annotation.tailrec

class FieldInPlay(val field: Field, val shape: Shape, val locationOfShape: Location) {
    private def maybeAtLocation(potentialLocation: Location) = {
        if(shape.canBePlacedIn(field, potentialLocation)) Some(new FieldInPlay(field, shape, potentialLocation))
        else None
    }
    lazy val currentField = field.withShapeAt(shape, locationOfShape)
    lazy val moveRight = maybeAtLocation(locationOfShape.right)
    lazy val moveLeft = maybeAtLocation(locationOfShape.left)
    lazy val moveDown = maybeAtLocation(locationOfShape.down)
    lazy val drop = {
        @tailrec def resultOfDrop(location: Location): Location = {
            val nextLocation = location.down
            if(!shape.canBePlacedIn(field, nextLocation)) location
            else resultOfDrop(nextLocation)
        }
        new FieldInPlay(field, shape, resultOfDrop(locationOfShape))
    }
}
