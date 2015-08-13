package bot

import filed._
import moves._

class Bot {

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndLocations(field: Field, shapeType: ShapeType): List[Location] = {
        val bottomLeftState = field.getCell(0, field.height-1).state;
        val asLowAsPossible = if(bottomLeftState == BLOCK || bottomLeftState == SOLID) field.height - 3 else field.height -2
        (0 to field.width - 2).map(x => Location(x, asLowAsPossible)).toList
    }
}
