package field

case class Shape(shapeType: ShapeType, degreesRotated: Int) {
    type CellGrid = Seq[Seq[CellType]]

    private def createGrid(size: Int, blockLocations: Location*) = {
       for (row <- 0 until size) yield for (column <- 0 until size) yield {
           if(blockLocations.contains(Location(column, row))) SHAPE
           else EMPTY
       }
    }

    private def rotate(grid: CellGrid, timesToRotate: Int): CellGrid  = {
        if(timesToRotate > 0) rotate(grid.transpose.map(x => x.reverse), timesToRotate - 1)
        else grid
    }

    private val unrotatedGrid = shapeType match {
        case O => createGrid(2, Location(0, 0), Location(0, 1), Location(1, 0), Location(1, 1))
        case I => createGrid(4, Location(0, 1), Location(1, 1), Location(2, 1), Location(3, 1))
    }

    val timesToRotate = degreesRotated / 90
    val grid = rotate(unrotatedGrid, timesToRotate)

    def locationsToOccupy(field: Field, location: Location): Seq[Location] = for (x <- 0 until grid.length; y <- 0 until grid.length; if grid(y)(x) == SHAPE) yield Location(location.x + x, location.y + y)

    def canBePlacedIn(field: Field, location: Location): Boolean = locationsToOccupy(field, location).map(loc => field.getCell(loc.x, loc.y)).forall(_.canBeOccupied)
}
