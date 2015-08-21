package bot

import field._
import moves._

class Bot {

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndStates(field: Field, shapeType: ShapeType): Seq[Field] = {
        shapeType match {
            case O =>   Shape(O, 0).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(O, 0), loc))

            case I =>   Shape(I, 0).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(I, 0), loc)) ++
                        Shape(I, 90).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(I, 90), loc))

            case J =>   Shape(J, 0).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(J, 0), loc)) ++
                        Shape(J, 90).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(J, 90), loc)) ++
                        Shape(J, 180).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(J, 180), loc)) ++
                        Shape(J, 270).allPossibleEndLocations(field).map(loc => field.withShapeAt(Shape(J, 270), loc))

        }
    }
}
