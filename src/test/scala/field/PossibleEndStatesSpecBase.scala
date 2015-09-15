package field

import org.scalatest._

trait PossibleEndStatesSpecBase {
    def possibleEndLocationsFor(field: Field, shapeType: ShapeType): Seq[Field] = {
        field.getPossibleEndStates(shapeType).map(_.field)
    }

    def resultOf(endStates: Field*): Seq[Field] = {
        List(endStates: _*)
    }
}
