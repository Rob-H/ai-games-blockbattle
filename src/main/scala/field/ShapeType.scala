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

    lazy val uniqueRotations = name match {
        case "O" => List(notRotated)
        case "I" | "S" | "Z" => List(notRotated, rotatedOnce)
        case "J" | "L" | "T" => List(notRotated, rotatedOnce, rotatedTwice, rotatedThreeTimes)
    }

    lazy val relativeBlockLocations = name match {
        case "O" => List(Location(0, 0), Location(0, 1), Location(1, 0), Location(1, 1))
        case "I" => List(Location(0, 1), Location(1, 1), Location(2, 1), Location(3, 1))
        case "J" => List(Location(0, 0), Location(0, 1), Location(1, 1), Location(2, 1))
        case "L" => List(Location(2, 0), Location(0, 1), Location(1, 1), Location(2, 1))
        case "S" => List(Location(1, 0), Location(2, 0), Location(0, 1), Location(1, 1))
        case "Z" => List(Location(0, 0), Location(1, 0), Location(1, 1), Location(2, 1))
        case "T" => List(Location(1, 0), Location(0, 1), Location(1, 1), Location(2, 1))
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
