package field

import field._
import org.scalatest._

class PossibleEndStatesForSSpec extends FunSpec with Matchers with PossibleEndStatesSpecBase {
    describe("for shape S") {
        it("for a 5 by 5 with one block in each corner field should return the correct states") {
            possibleEndLocationsFor(new Field(
                "0,1,1,0,0;" +
                "1,1,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "2,0,0,0,2"), S) should === (resultOf(
                    new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,1,1,0,0;" +
                        "1,1,0,0,0;" +
                        "2,0,0,0,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,1,1,0;" +
                        "2,1,1,0,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,1,1;" +
                        "2,0,1,1,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "1,0,0,0,0;" +
                        "1,1,0,0,0;" +
                        "2,1,0,0,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,1,0,0,0;" +
                        "0,1,1,0,0;" +
                        "2,0,1,0,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,1,0,0;" +
                        "0,0,1,1,0;" +
                        "2,0,0,1,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,1,0;" +
                        "0,0,0,1,1;" +
                        "0,0,0,0,1;" +
                        "2,0,0,0,2"
                    )
                ))
        }
    }
}
