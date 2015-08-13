package filed

/**
 * User: goodg_000
 * Date: 06.07.2015
 * Time: 22:48
 */
class Field(val width: Int, val height: Int, private val fieldString : String) {

  private val grid : Array[Array[Cell]] = parse(fieldString)

  /**
   * Parses the input string to get a grid with Cell objects
   * @param fieldString : input string
   */
  def parse(fieldString: String) : Array[Array[Cell]] = {
    val res = Array.ofDim[Cell](width, height)
    val rows = fieldString.split(";")

    for (y <- 0 to height-1) {
      val rowCells = rows(y).split(",")

      for (x <- 0 to width-1) {
        val cellCode = Integer.parseInt(rowCells(x))
        res(x)(y) = new Cell(x, y, CellType.apply(cellCode))
      }
    }

    res
  }

  def getCell(x: Int, y: Int):Cell = grid(x)(y)

}
