package field

import scala.annotation.tailrec

class FieldInPlay(val field: Field, val shape: Shape, val locationOfShape: Location) {
    lazy val currentField = field.withShapeAt(shape, locationOfShape)
    lazy val moveRight = new FieldInPlay(field, shape, Location(locationOfShape.x + 1, locationOfShape.y))
    lazy val moveLeft = new FieldInPlay(field, shape, Location(locationOfShape.x - 1, locationOfShape.y))
    lazy val drop = {
        @tailrec def resultOfDrop(location: Location): Location = {
            val nextLocation = Location(location.x, location.y + 1)
            if(!shape.canBePlacedIn(field, nextLocation)) location
            else resultOfDrop(nextLocation)
        }
        new FieldInPlay(field, shape, resultOfDrop(locationOfShape))
    }
}
