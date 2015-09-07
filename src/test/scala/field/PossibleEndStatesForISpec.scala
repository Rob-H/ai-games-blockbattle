package field

import field._
import org.scalatest._

class PossibleEndStatesForISpec extends FunSpec with Matchers with PossibleEndStatesSpecBase {
    describe("for shape I") {
        it("for an empty 4 by 4 field should return the correct states") {
            possibleEndLocationsFor(new Field(
                "1,1,1,1;" +
                "0,0,0,0;" +
                "0,0,0,0;" +
                "0,0,0,0"), I) should === (resultOf(
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
                ))
        }

        it("for an empty 4 by 5 field should return the correct states") {
            possibleEndLocationsFor(new Field(
                "1,1,1,1,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0"), I) should === (resultOf(
                    new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "1,1,1,1,0"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,1,1,1,1"
                    ), new Field(
                        "1,0,0,0,0;" +
                        "1,0,0,0,0;" +
                        "1,0,0,0,0;" +
                        "1,0,0,0,0"
                    ), new Field(
                        "0,1,0,0,0;" +
                        "0,1,0,0,0;" +
                        "0,1,0,0,0;" +
                        "0,1,0,0,0"
                    ), new Field(
                        "0,0,1,0,0;" +
                        "0,0,1,0,0;" +
                        "0,0,1,0,0;" +
                        "0,0,1,0,0"
                    ), new Field(
                        "0,0,0,1,0;" +
                        "0,0,0,1,0;" +
                        "0,0,0,1,0;" +
                        "0,0,0,1,0"
                    ), new Field(
                        "0,0,0,0,1;" +
                        "0,0,0,0,1;" +
                        "0,0,0,0,1;" +
                        "0,0,0,0,1"
                    )
                ))
        }

        it("for a 5 by 5 with one block in the corner field should return the correct states") {
            possibleEndLocationsFor(new Field(
                "1,1,1,1,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "2,0,0,0,0"), I) should === (resultOf(
                    new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "1,1,1,1,0;" +
                        "2,0,0,0,0"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "2,1,1,1,1"
                    ), new Field(
                        "1,0,0,0,0;" +
                        "1,0,0,0,0;" +
                        "1,0,0,0,0;" +
                        "1,0,0,0,0;" +
                        "2,0,0,0,0"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,1,0,0,0;" +
                        "0,1,0,0,0;" +
                        "0,1,0,0,0;" +
                        "2,1,0,0,0"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,1,0,0;" +
                        "0,0,1,0,0;" +
                        "0,0,1,0,0;" +
                        "2,0,1,0,0"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,1,0;" +
                        "0,0,0,1,0;" +
                        "0,0,0,1,0;" +
                        "2,0,0,1,0"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,1;" +
                        "0,0,0,0,1;" +
                        "0,0,0,0,1;" +
                        "2,0,0,0,1"
                    )
                ))
        }
    }
}
