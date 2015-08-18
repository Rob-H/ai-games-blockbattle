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
                val leftCell = field.getCell(xLocation, lowest + 1)
                val rightCell = field.getCell(xLocation + 1, lowest + 1)
                if(leftCell.cannotBeOccupied || rightCell.cannotBeOccupied) asLowAsPossibleIter(lowest-1) else lowest
            }
            asLowAsPossibleIter(field.height-2)
        }
        shapeType match {
            case O => (0 to field.width - 2).map(x => field.withShapeAt(shapeType, Location(x, asLowAsPossible(x))))
            case I => List(
                    new Field(
                        "0,0,0,0;" +
                        "0,0,0,0;" +
                        "0,0,0,0;" +
                        "1,1,1,1"
                    ), new Field(
                        "1,0,0,0;" +
                        "1,0,0,0;" +
                        "1,0,0,0;" +
                        "1,0,0,0"
                    ), new Field(
                        "0,1,0,0;" +
                        "0,1,0,0;" +
                        "0,1,0,0;" +
                        "0,1,0,0"
                    ), new Field(
                        "0,0,1,0;" +
                        "0,0,1,0;" +
                        "0,0,1,0;" +
                        "0,0,1,0"
                    ), new Field(
                        "0,0,0,1;" +
                        "0,0,0,1;" +
                        "0,0,0,1;" +
                        "0,0,0,1"
                    )
                )
        }
    }
}
