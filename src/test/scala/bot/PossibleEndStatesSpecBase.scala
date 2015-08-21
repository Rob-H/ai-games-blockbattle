package bot;

import field._
import org.scalatest._

trait PossibleEndStatesSpecBase {
    def possibleEndLocationsFor(field: Field, shapeType: ShapeType): Seq[Field] = {
        new Bot().getPossibleEndStates(field, shapeType)
    }

    def resultOf(endStates: Field*): Seq[Field] = {
        List(endStates: _*)
    }
}
