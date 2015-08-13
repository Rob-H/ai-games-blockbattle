package bot

import filed.{Field, ShapeType, Location}
import moves._

class Bot {

    def getMoves(state: BotState, timeout: Long): List[MoveType] = {
        List()
    }

    def getPossibleEndLocations(field: Field, shapeType: ShapeType, shapeLocation: Location): List[Location] = {
        val base = if(field.width > 2) List(Location(1, 0)) else Nil
        shapeLocation :: base
    }
}
