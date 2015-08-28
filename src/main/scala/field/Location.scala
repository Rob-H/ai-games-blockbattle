package field;

case class Location(val x: Int, val y: Int) {
    lazy val left = Location(x - 1, y)
    lazy val right = Location(x + 1, y)
    lazy val down = Location(x, y + 1)
}
