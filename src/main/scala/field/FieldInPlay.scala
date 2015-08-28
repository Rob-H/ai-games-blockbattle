package field

import scala.annotation.tailrec

class FieldInPlay(val field: Field, val shape: Shape, val locationOfShape: Location) {
    private def maybeAtLocation(potentialLocation: Location) = {
        if(shape.canBePlacedIn(field, potentialLocation)) Some(new FieldInPlay(field, shape, potentialLocation))
        else None
    }
    lazy val currentField = field.withShapeAt(shape, locationOfShape)
    lazy val moveRight = maybeAtLocation(Location(locationOfShape.x + 1, locationOfShape.y))
    lazy val moveLeft = maybeAtLocation(Location(locationOfShape.x - 1, locationOfShape.y))
    lazy val drop = {
        @tailrec def resultOfDrop(location: Location): Location = {
            val nextLocation = Location(location.x, location.y + 1)
            if(!shape.canBePlacedIn(field, nextLocation)) location
            else resultOfDrop(nextLocation)
        }
        new FieldInPlay(field, shape, resultOfDrop(locationOfShape))
    }
}
