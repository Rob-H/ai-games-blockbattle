package field

import scala.annotation.tailrec

case class Shape(val shapeType: ShapeType, val degreesRotated: Int = 0) {
    type CellGrid = Seq[Seq[CellType]]
    private val oneRotation = 90

    private def createGrid(blockLocations: Seq[Location]) = {
        val size = blockLocations.foldLeft(0)((currentLargest, location) => math.max(currentLargest, math.max(location.x, location.y)))

        for (row <- 0 to size) yield for (column <- 0 to size) yield {
            if(blockLocations.contains(Location(column, row))) SHAPE
            else EMPTY
        }
    }

    @tailrec private def rotateClockwise(grid: CellGrid, timesToRotate: Int): CellGrid  = {
        lazy val rotateAntiClockwiseOnce = grid.transpose.reverse
        lazy val rotateClockwiseOnce = grid.transpose.map(x => x.reverse)

        if(timesToRotate == 3) rotateAntiClockwiseOnce
        else if(timesToRotate > 0) rotateClockwise(rotateClockwiseOnce, timesToRotate - 1)
        else grid
    }

    private val unrotatedGrid = createGrid(shapeType.relativeBlockLocations)

    private val timesToRotate = 4 + ((degreesRotated / oneRotation) % 4)

    private val grid = rotateClockwise(unrotatedGrid, timesToRotate)
    private lazy val locationsOfBlocks = for (x <- 0 until grid.length; y <- 0 until grid.length; if grid(y)(x) == SHAPE) yield Location(x, y)

    def locationsToOccupy(location: Location): Seq[Location] = locationsOfBlocks.map{ case Location(x, y) => Location(location.x + x, location.y + y) }

    def canBePlacedIn(field: Field, location: Location): Boolean =
        locationsToOccupy(location).forall(loc =>
            loc.x >= 0 &&
            loc.x < field.width &&
            loc.y < field.height &&
            (loc.y < 0 || field.getCell(loc.x, loc.y).canBeOccupied)
        )

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

    lazy val turnRight = Shape(shapeType, degreesRotated + oneRotation)
    lazy val turnLeft = Shape(shapeType, degreesRotated - oneRotation)
}
