package bot

import filed._
import moves._
import scala.annotation.tailrec

class Bot {

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndLocations(field: Field, shapeType: ShapeType): List[Location] = {
        def isSolidOrBlock(cell: CellType) = cell == BLOCK || cell == SOLID
        def asLowAsPossible(xLocation: Int) = {
            @tailrec def asLowAsPossibleIter(lowest: Int): Int = {
                val leftCell = field.getCell(xLocation, lowest + 1).state;
                val rightCell = field.getCell(xLocation + 1, lowest + 1).state;
                if(isSolidOrBlock(leftCell) || isSolidOrBlock(rightCell)) asLowAsPossibleIter(lowest-1) else lowest
            }
            asLowAsPossibleIter(field.height-2)
        }
        (0 to field.width - 2).map(x => Location(x, asLowAsPossible(x))).toList
    }
}
