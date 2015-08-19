package bot

import field._
import moves._
import scala.annotation.tailrec

class Bot {

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndStates(field: Field, shapeType: ShapeType): Seq[Field] = {
        def asLowAsPossible(xLocation: Int) = {
            @tailrec def asLowAsPossibleIter(lowest: Int): Int = {
                val cells = List(field.getCell(xLocation, lowest + 1), field.getCell(xLocation + 1, lowest + 1))
                if(cells.exists(_.cannotBeOccupied)) asLowAsPossibleIter(lowest-1) else lowest
            }
            asLowAsPossibleIter(field.height-2)
        }
        shapeType match {
            case O => (0 to field.width - 2).map(x => field.withShapeAt(shapeType, Location(x, asLowAsPossible(x))))
            case I => ((0 to field.width - 4).map(x => Location(x, 1, 0)) ++ (-2 to field.width -3).map(x => Location(x, 0, 90))).map(loc => field.withShapeAt(shapeType, loc))
        }
    }
}
