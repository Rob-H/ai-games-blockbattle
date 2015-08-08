// scalastyle:off
package filed

import java.awt.Point

/**
 * User: goodg_000
 * Date: 06.07.2015
 * Time: 0:30
 */
class Cell(var location : Point = null, var state : CellType = EMPTY) {

  def this(x: Int, y: Int, cellType: CellType) {
    this()
    this.location = new Point(x, y)
    this.state = cellType
  }

  def setShape(): Unit = {
    this.state = SHAPE
  }

  def setLocation(x: Int, y: Int): Unit = {
    if (location == null) {
      location = new Point()
    }
    location.setLocation(x, y)
  }

  def isShape : Boolean = state == SHAPE
  def isSolid : Boolean = state == SOLID
  def isBlock : Boolean = state == BLOCK
  def isEmpty : Boolean = state == EMPTY

}
