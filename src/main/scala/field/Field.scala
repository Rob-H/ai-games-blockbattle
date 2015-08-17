package field

/**
 * User: goodg_000
 * Date: 06.07.2015
 * Time: 22:48
 */
class Field(fieldString : String) {
    //an array of the rows
    private val grid = fieldString.split(";").map(row => row.split(",").map(cell => CellType(Integer.parseInt(cell))).toArray).toArray

    val height = grid.length
    val width = grid(0).length

    def getCell(x: Int, y: Int):CellType = grid(y)(x)
}
