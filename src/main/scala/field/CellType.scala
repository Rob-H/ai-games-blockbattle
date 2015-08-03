package filed

/**
 * CellType class
 *
 * Enum of all the possible Cell types
 *
 * User: goodg_000
 * Date: 06.07.2015
 * Time: 0:25
 */
case class CellType(code: Int) {
}

object EMPTY extends CellType(0)
object SHAPE extends CellType(1)
object BLOCK extends CellType(2)
object SOLID extends CellType(3)
