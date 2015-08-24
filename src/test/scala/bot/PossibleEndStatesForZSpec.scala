package bot

import field._
import org.scalatest._

class PossibleEndStatesForZSpec extends FunSpec with Matchers with PossibleEndStatesSpecBase {
    describe("for shape Z") {
        it("for a 5 by 5 with two blocks in each corner field should return the correct states") {
            possibleEndLocationsFor(new Field(
                "1,1,0,0,0;" +
                "0,1,1,0,0;" +
                "0,0,0,0,0;" +
                "2,0,0,0,2;" +
                "2,0,0,0,2"), Z) should === (resultOf(
                    new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "1,1,0,0,0;" +
                        "2,1,1,0,2;" +
                        "2,0,0,0,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "2,1,1,0,2;" +
                        "2,0,1,1,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,1,1,0;" +
                        "0,0,0,1,1;" +
                        "2,0,0,0,2;" +
                        "2,0,0,0,2"
                    ), new Field(
                        "0,1,0,0,0;" +
                        "1,1,0,0,0;" +
                        "1,0,0,0,0;" +
                        "2,0,0,0,2;" +
                        "2,0,0,0,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,1,0,0;" +
                        "2,1,1,0,2;" +
                        "2,1,0,0,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,0;" +
                        "0,0,0,1,0;" +
                        "2,0,1,1,2;" +
                        "2,0,1,0,2"
                    ), new Field(
                        "0,0,0,0,0;" +
                        "0,0,0,0,1;" +
                        "0,0,0,1,1;" +
                        "2,0,0,1,2;" +
                        "2,0,0,0,2"
                    )
                ))
        }
    }
}
