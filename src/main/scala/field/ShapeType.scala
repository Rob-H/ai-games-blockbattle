package field

/**
 * ShapeType class
 *
 * Enum for all possible Shape types
 *
 * User: goodg_000
 * Date: 06.07.2015
 * Time: 22:33
 */
case class ShapeType(name: String) {
    val notRotated = 0;
    val rotatedOnce = 90;
    val rotatedTwice = 180;
    val rotatedThreeTimes = 270;

    val uniqueRotations = name match {
        case "O" => List(notRotated)
        case "I" | "S" | "Z" => List(notRotated, rotatedOnce)
        case "J" | "L" | "T" => List(notRotated, rotatedOnce, rotatedTwice, rotatedThreeTimes)
    }
}

object I extends ShapeType("I")
object J extends ShapeType("J")
object L extends ShapeType("L")
object O extends ShapeType("O")
object S extends ShapeType("S")
object T extends ShapeType("T")
object Z extends ShapeType("Z")
object NONE extends ShapeType("NONE")
