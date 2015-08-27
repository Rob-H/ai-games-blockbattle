package field

class FieldInPlay(val field: Field, val shape: Shape, val locationOfShape: Location) {
    lazy val currentField = field.withShapeAt(shape, locationOfShape)
    lazy val moveRight = new FieldInPlay(field, shape, Location(locationOfShape.x + 1, locationOfShape.y))
    lazy val moveLeft= new FieldInPlay(field, shape, Location(locationOfShape.x - 1, locationOfShape.y))
}
