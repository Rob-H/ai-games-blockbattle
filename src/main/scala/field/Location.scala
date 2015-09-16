package field;

case class Location(x: Int, y: Int) {
    lazy val left = Location(x - 1, y)
    lazy val right = Location(x + 1, y)
    lazy val down = Location(x, y + 1)
}
