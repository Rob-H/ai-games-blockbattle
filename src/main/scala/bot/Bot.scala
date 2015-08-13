package bot

import filed._
import moves._

class Bot {

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndLocations(field: Field, shapeType: ShapeType): List[Location] = {
        def isSolidOrBlock(cell: CellType) = cell == BLOCK || cell == SOLID
        def asLowAsPossible(xLocation: Int) = {
            val leftCell = field.getCell(xLocation, field.height-1).state;
            val rightCell = field.getCell(xLocation + 1, field.height-1).state;
            if(isSolidOrBlock(leftCell) || isSolidOrBlock(rightCell)) field.height - 3 else field.height -2
        }
        (0 to field.width - 2).map(x => Location(x, asLowAsPossible(x))).toList
    }
}
