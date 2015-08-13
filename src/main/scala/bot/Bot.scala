package bot

import filed._
import moves._

class Bot {

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndLocations(field: Field, shapeType: ShapeType, shapeLocation: Location): List[Location] = {
        val asLowAsPossible = if(field.getCell(0, field.height-1).state == BLOCK) field.height - 3 else field.height -2
        (0 to field.width - 2).map(x => Location(x, asLowAsPossible)).toList
    }
}
