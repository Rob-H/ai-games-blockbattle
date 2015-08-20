package field

case class Field(val fieldString : String) {
    //an array of the rows
    private val grid = fieldString.split(";").map(row => row.split(",").map(cell => CellType(Integer.parseInt(cell))).toArray).toArray

    val height = grid.length
    val width = grid(0).length

    def getCell(x: Int, y: Int):CellType = grid(y)(x)

    def withShapeAt(shape: Shape): Field = {
        val locationsToOccupy = shape.locationsToOccupy(this);

        def newCellType(x: Int, y: Int) = {
            val oldCellType = getCell(x, y)
            val newCellShouldBeShape = locationsToOccupy.contains((x, y))
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
