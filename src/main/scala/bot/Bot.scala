package bot

import field._
import moves._

class Bot {
    val notRotated = 0;
    val rotatedOnce = 90;
    val rotatedTwice = 180;
    val rotatedThreeTimes = 270;

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndStates(field: Field, shapeType: ShapeType): Seq[Field] = {
        shapeType match {
            case O =>   Shape(O, notRotated).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(O, notRotated), loc))

            case I =>   Shape(I, notRotated).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(I, notRotated), loc)) ++
                        Shape(I, rotatedOnce).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(I, rotatedOnce), loc))

            case J =>   Shape(J, notRotated).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(J, notRotated), loc)) ++
                        Shape(J, rotatedOnce).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(J, rotatedOnce), loc)) ++
                        Shape(J, rotatedTwice).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(J, rotatedTwice), loc)) ++
                        Shape(J, rotatedThreeTimes).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(J, rotatedThreeTimes), loc))

            case L =>   Shape(L, notRotated).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(L, notRotated), loc)) ++
                        Shape(L, rotatedOnce).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(L, rotatedOnce), loc)) ++
                        Shape(L, rotatedTwice).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(L, rotatedTwice), loc)) ++
                        Shape(L, rotatedThreeTimes).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(L, rotatedThreeTimes), loc))

            case S =>   Shape(S, notRotated).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(S, notRotated), loc)) ++
                        Shape(S, rotatedOnce).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(S, rotatedOnce), loc))

            case Z =>   Shape(Z, notRotated).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(Z, notRotated), loc)) ++
                        Shape(Z, rotatedOnce).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(Z, rotatedOnce), loc))
        }
    }
}
