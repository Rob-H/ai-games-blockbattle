package field

import scala.annotation.tailrec

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
        case J => createGrid(3, Location(0, 0), Location(0, 1), Location(1, 1), Location(2, 1))
        case L => createGrid(3, Location(2, 0), Location(0, 1), Location(1, 1), Location(2, 1))
    }

    private val timesToRotate = degreesRotated / 90

    private val grid = rotate(unrotatedGrid, timesToRotate)
    private lazy val locationsOfBlocks = for (x <- 0 until grid.length; y <- 0 until grid.length; if grid(y)(x) == SHAPE) yield Location(x, y)

    def locationsToOccupy(location: Location): Seq[Location] = locationsOfBlocks.map{ case Location(x, y) => Location(location.x + x, location.y + y) }

    def canBePlacedIn(field: Field, location: Location): Boolean = locationsToOccupy(location).map(loc => field.getCell(loc.x, loc.y)).forall(_.canBeOccupied)

    def allPossibleEndLocations(field: Field): Seq[Location] = {
        val asLeftAsPossible = 0 - locationsOfBlocks.foldLeft(field.width)(_ min _.x)
        val asRightAsPossible = field.width - 1 - locationsOfBlocks.foldLeft(0)(_ max _.x)
        val asLowAsPossible = field.height - 1 - locationsOfBlocks.foldLeft(0)(_ max _.y)

        def lowestValidPosition(xLocation: Int) = {
            @tailrec def lowestValidPositionIter(lowest: Int): Int = {
                if(canBePlacedIn(field, Location(xLocation, lowest))) lowest
                else lowestValidPositionIter(lowest-1)
            }
            lowestValidPositionIter(asLowAsPossible)
        }
        for (x <- asLeftAsPossible to asRightAsPossible) yield Location(x, lowestValidPosition(x))
    }
}
