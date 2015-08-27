package field

case class CellType(val code: Int) {

    val isShape : Boolean = this == SHAPE
    val isSolid : Boolean = this == SOLID
    val isBlock : Boolean = this == BLOCK
    val isEmpty : Boolean = this == EMPTY
    val cannotBeOccupied: Boolean = isBlock || isSolid
    val canBeOccupied: Boolean = !cannotBeOccupied
}

object EMPTY extends CellType(0)
object SHAPE extends CellType(1)
object BLOCK extends CellType(2)
object SOLID extends CellType(3)
