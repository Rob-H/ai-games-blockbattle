package field

class Shape(shapeType: ShapeType, location: Location) {
    type CellGrid = Seq[Seq[CellType]]

    private def createGrid(size: Int, blockLocations: Tuple2[Int, Int]*) = {
       for (row <- 0 until size) yield for (column <- 0 until size) yield {
           if(blockLocations.contains((column, row))) SHAPE
           else EMPTY
       }
    }

    private def rotate(grid: CellGrid, timesToRotate: Int): CellGrid  = {
        if(timesToRotate > 0) rotate(grid.transpose.map(x => x.reverse), timesToRotate - 1)
        else grid
    }

    private val unrotatedGrid = shapeType match {
        case O => createGrid(2, (0, 0), (0, 1), (1, 0), (1, 1))
        case I => createGrid(4, (0, 1), (1, 1), (2, 1), (3, 1))
    }

    val timesToRotate = location.degrees / 90
    val grid = rotate(unrotatedGrid, timesToRotate)

    def canBePlacedIn(field: Field): Boolean = {
        val cellsToOccupy =
            for (x <- 0 until grid.length; y <- 0 until grid.length; if grid(y)(x) == SHAPE)
                yield field.getCell(location.x + x, location.y + y)
        cellsToOccupy.forall(_.canBeOccupied)
    }
}
