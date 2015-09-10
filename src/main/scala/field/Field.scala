package field

case class Field(val fieldString : String) {
    //an array of the rows
    private val grid = fieldString.split(";").map(row => row.split(",").map(cell => CellType(Integer.parseInt(cell))).toArray).toArray

    val height = grid.length
    val width = grid(0).length

    def getCell(x: Int, y: Int):CellType = grid(y)(x)

    lazy val heuristic = {
        def thereAreAnyCellsAboveThatAreNotEmpty(columnIndex: Int, rowIndex: Int) = {
            val allCellsAboveThatAreNotPartOfAFullRow = for {
                r <- (rowIndex - 1) to 0 by -1
                if(grid(r) exists (_.isEmpty))
            } yield (grid(r)(columnIndex))

            allCellsAboveThatAreNotPartOfAFullRow exists (!_.isEmpty)
        }

        val fullRowsHeuristic = grid.foldLeft(0) {(acc, row) => {
            val emptySquares = (row filter(_.isEmpty)).length
            if (emptySquares == width) acc
            else if (emptySquares == 0) acc + (width)
            else acc - (emptySquares/2)
        }}

        val numberOfEmptyBlocksWithBlockAbove = (for {
            rowIndex <- 1 until height
            columnIndex <- 0 until width
        } yield {
            val cell = getCell(columnIndex, rowIndex)
            if(cell.isEmpty && thereAreAnyCellsAboveThatAreNotEmpty(columnIndex, rowIndex)) 1
            else 0
        }).sum

        fullRowsHeuristic - (numberOfEmptyBlocksWithBlockAbove * width)
    }


    def withShapeAt(shape: Shape, location: Location): Field = {
        val locationsToOccupy = shape.locationsToOccupy(location);

        def newCellType(x: Int, y: Int) = {
            val oldCellType = getCell(x, y)
            val newCellShouldBeShape = locationsToOccupy.contains(Location(x, y))
            oldCellType match {
                case nonOccupiable @ (SOLID | BLOCK) if !newCellShouldBeShape => nonOccupiable
                case SHAPE | EMPTY => if(newCellShouldBeShape) SHAPE else EMPTY
            }
        }

        val fieldString = (0 until height).map(y => (0 until width).map(x => newCellType(x, y).code).mkString(",")).mkString(";")
        Field(fieldString);
    }

    lazy val finalise: Field = {
        val gridWithShapeAsBlock = grid.map(row => row.map { _ match {
            case SHAPE => BLOCK
            case shapeType @ _ => shapeType
        }})

        val fieldWithoutFullRows = (for {
            row <- gridWithShapeAsBlock
            if(row.exists(cell => !cell.isBlock))
        } yield (row)).toArray

        val numberOfRowsToAdd = height - fieldWithoutFullRows.length

        val finalField = Array.fill(numberOfRowsToAdd)(Array.fill(width)(EMPTY)) ++ fieldWithoutFullRows

        val fieldString = finalField.map(row => row.map(_.code).mkString(",")).mkString(";")
        Field(fieldString);
    }

    def getPossibleEndStates(shapeType: ShapeType): Seq[Field] = {
        for {
            rotatedShape <- shapeType.uniqueRotations.map(Shape(shapeType, _))
            loc <- rotatedShape.allPossibleEndLocations(this)
        } yield this.withShapeAt(rotatedShape, loc)
    }

    override val toString = fieldString
}
