package field

class Cell(x: Int, y: Int, val cellType: CellType = EMPTY) {

    val location = Location(x, y)

    val isShape : Boolean = cellType == SHAPE
    val isSolid : Boolean = cellType == SOLID
    val isBlock : Boolean = cellType == BLOCK
    val isEmpty : Boolean = cellType == EMPTY
    val cannotBeOccupied: Boolean = cellType == BLOCK || cellType == SOLID
}
