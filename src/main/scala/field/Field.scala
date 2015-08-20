package field

case class Field(val fieldString : String) {
    //an array of the rows
    private val grid = fieldString.split(";").map(row => row.split(",").map(cell => CellType(Integer.parseInt(cell))).toArray).toArray

    val height = grid.length
    val width = grid(0).length

    def getCell(x: Int, y: Int):CellType = grid(y)(x)

    def withShapeAt(shapeType: ShapeType, location: Location): Field = {
        def newCellType(x: Int, y: Int) = {
            val oldCellType = getCell(x, y)
            val newCellShouldBeShape = (shapeType, location) match {
                case (O, _) =>  x >= location.x && x <= (location.x + 1) && y >= location.y && y <= (location.y + 1)
                case (I, Location(_, _, 0)) => y == location.y + 1 && x >= location.x && x <= location.x + 3
                case (I, Location(_, _, 90)) => x == location.x + 2 && y >= location.y && y <= location.y + 3            
            }
            oldCellType match {
                case nonOccupiable @ (SOLID | BLOCK) if !newCellShouldBeShape => nonOccupiable
                case SHAPE | EMPTY => if(newCellShouldBeShape) SHAPE else EMPTY
            }
        }
        val fieldString = (0 until height).map(y => (0 until width).map(x => newCellType(x, y).code).mkString(",")).mkString(";")
        Field(fieldString);
    }

    override val toString = fieldString
}
