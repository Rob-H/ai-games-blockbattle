package bot

import field._
import moves._
import scala.annotation.tailrec

class Bot {

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndStates(field: Field, shapeType: ShapeType): Seq[Field] = {
        def asLowAsPossible(xLocation: Int, ylocation: Int, degrees: Int) = {
            @tailrec def asLowAsPossibleIter(lowest: Int): Int = {
                if(Shape(shapeType, Location(xLocation, lowest, degrees)).canBePlacedIn(field)) lowest
                else asLowAsPossibleIter(lowest-1)
            }
            asLowAsPossibleIter(ylocation)
        }
        shapeType match {
            case O => (0 to field.width - 2).map(x => field.withShapeAt(Shape(shapeType, Location(x, asLowAsPossible(x, field.height-2, 0)))))
            case I => ((0 to field.width - 4).map(x => Location(x, asLowAsPossible(x, field.height - 2, 0), 0)) ++ (-2 to field.width -3).map(x => Location(x, asLowAsPossible(x, field.height - 4, 90), 90))).map(loc => field.withShapeAt(Shape(shapeType, loc)))
        }
    }
}
