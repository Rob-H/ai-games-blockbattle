package field

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

    val isShape : Boolean = this == SHAPE
    val isSolid : Boolean = this == SOLID
    val isBlock : Boolean = this == BLOCK
    val isEmpty : Boolean = this == EMPTY
    val cannotBeOccupied: Boolean = this == BLOCK || this == SOLID
}

object EMPTY extends CellType(0)
object SHAPE extends CellType(1)
object BLOCK extends CellType(2)
object SOLID extends CellType(3)
