package bot

import filed.{Field, ShapeType, Location}
import moves._

class Bot {

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndLocations(field: Field, shapeType: ShapeType, shapeLocation: Location): List[Location] = {
        val bottom = field.height - 2
        (0 to field.width - 2).map(x => Location(x, bottom)).toList
    }
}
