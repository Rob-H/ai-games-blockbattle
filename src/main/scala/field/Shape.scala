package filed

import java.awt.Point

/**
 * Shape class
 *
 * Represents the shapes that appear in the field.
 * Some basic methods have already been implemented, but
 * actual move actions, etc. should still be created.
 *
 * User: goodg_000
 * Date: 06.07.2015
 * Time: 22:43
 */
class Shape(val shapeType : ShapeType, val field : Field, val location : Point) {

  private var shape : Array[Array[Cell]] = null
  private var size : Int = 0
  private val blocks : Array[Cell] = new Array[Cell](4)

  setShape()
  setBlockLocations()

  private def setBlockLocations() : Unit = {
    val range = 0 to size-1
    for (y <- range; x <- range) {
      if (shape(x)(y).isShape) {
        shape(x)(y).setLocation(location.x + x, location.y + y)
      }
    }
  }

  /**
   * Set shape in square box creates new Cells that can be checked against the actual
	 * playing field.
   */
  private def setShape(): Unit = {
    shapeType match {
      case I =>
        size = 4
        shape = createShape()
        blocks(0) = shape(0)(1)
        blocks(1) = shape(1)(1)
        blocks(2) = shape(2)(1)
        blocks(3) = shape(3)(1)
      case J =>
        size = 3
        shape = createShape()
        blocks(0) = shape(0)(0)
        blocks(1) = shape(0)(1)
        blocks(2) = shape(1)(1)
        blocks(3) = shape(2)(1)
      case L =>
        size = 3
        shape = createShape()
        blocks(0) = shape(2)(0)
        blocks(1) = shape(0)(1)
        blocks(2) = shape(1)(1)
        blocks(3) = shape(2)(1)
      case O =>
        size = 2
        shape = createShape()
        blocks(0) = shape(0)(0)
        blocks(1) = shape(1)(0)
        blocks(2) = shape(0)(1)
        blocks(3) = shape(1)(1)
      case S =>
        size = 2
        shape = createShape()
        blocks(0) = shape(1)(0)
        blocks(1) = shape(2)(0)
        blocks(2) = shape(0)(1)
        blocks(3) = shape(1)(1)
      case T =>
        size = 2
        shape = createShape()
        blocks(0) = shape(1)(0)
        blocks(1) = shape(0)(1)
        blocks(2) = shape(1)(1)
        blocks(3) = shape(2)(1)
      case Z =>
        size = 2
        shape = createShape()
        blocks(0) = shape(0)(0)
        blocks(1) = shape(1)(0)
        blocks(2) = shape(1)(1)
        blocks(3) = shape(2)(1)
    }

    // set type to SHAPE
    blocks.foreach(b => b.setShape())
  }

  private def createShape() : Array[Array[Cell]] = Array.fill(size, size)(new Cell())

}
