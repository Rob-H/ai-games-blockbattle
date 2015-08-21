package bot;

import field._
import org.scalatest._

class PossibleEndStatesForOSpec extends FunSpec with Matchers with PossibleEndStatesSpecBase {
    describe("for shape O") {
        describe("and an empty field") {
            it("that is only as big as an O, should return correct state") {
                possibleEndLocationsFor(new Field(
                    "1,1;" +
                    "1,1"), O) should === (resultOf(
                        new Field(
                            "1,1;" +
                            "1,1"
                        )
                    ))
            }

            it("that is 3 by 2, should return correct states") {
                possibleEndLocationsFor(new Field(
                    "1,1,0;" +
                    "1,1,0"), O) should === (resultOf(
                        new Field(
                            "1,1,0;" +
                            "1,1,0"
                        ), new Field(
                            "0,1,1;" +
                            "0,1,1"
                        )
                    ))
            }

            it("that is 4 by 2, should return correct states") {
                possibleEndLocationsFor(new Field(
                    "1,1,0,0;" +
                    "1,1,0,0"), O) should === (resultOf(
                        new Field(
                            "1,1,0,0;" +
                            "1,1,0,0"
                        ), new Field(
                            "0,1,1,0;" +
                            "0,1,1,0"
                        ), new Field(
                            "0,0,1,1;" +
                            "0,0,1,1"
                        )
                    ))
            }

            it("that is 4 by 3, should return correct states") {
                possibleEndLocationsFor(new Field(
                    "1,1,0,0;" +
                    "1,1,0,0;" +
                    "0,0,0,0"), O) should === (resultOf(
                        new Field(
                            "0,0,0,0;" +
                            "1,1,0,0;" +
                            "1,1,0,0"
                        ), new Field(
                            "0,0,0,0;" +
                            "0,1,1,0;" +
                            "0,1,1,0"
                        ), new Field(
                            "0,0,0,0;" +
                            "0,0,1,1;" +
                            "0,0,1,1"
                        )
                    ))
            }
        }

        describe("a field with blocks all along the bottom") {
            it("in a 4 by 3 grid, should return the correct states") {
                possibleEndLocationsFor(new Field(
                    "1,1,0,0;" +
                    "1,1,0,0;" +
                    "2,2,2,2"), O) should === (resultOf(
                        new Field(
                            "1,1,0,0;" +
                            "1,1,0,0;" +
                            "2,2,2,2"
                        ), new Field(
                            "0,1,1,0;" +
                            "0,1,1,0;" +
                            "2,2,2,2"
                        ), new Field(
                            "0,0,1,1;" +
                            "0,0,1,1;" +
                            "2,2,2,2"
                        )
                    ))
            }
        }

        describe("a field with solids all along the bottom") {
            it("in a 4 by 3 grid, should return the correct states") {
                possibleEndLocationsFor(new Field(
                    "1,1,0,0;" +
                    "1,1,0,0;" +
                    "3,3,3,3"), O) should === (resultOf(
                        new Field(
                            "1,1,0,0;" +
                            "1,1,0,0;" +
                            "3,3,3,3"
                        ), new Field(
                            "0,1,1,0;" +
                            "0,1,1,0;" +
                            "3,3,3,3"
                        ), new Field(
                            "0,0,1,1;" +
                            "0,0,1,1;" +
                            "3,3,3,3"
                        )
                    ))
            }
        }

        describe("a field with uneven blocks") {
            it("in a 4 by 3 grid with one block in the left corner, should return the correct states") {
                possibleEndLocationsFor(new Field(
                    "1,1,0,0;" +
                    "1,1,0,0;" +
                    "2,0,0,0"), O) should === (resultOf(
                        new Field(
                            "1,1,0,0;" +
                            "1,1,0,0;" +
                            "2,0,0,0"
                        ), new Field(
                            "0,0,0,0;" +
                            "0,1,1,0;" +
                            "2,1,1,0"
                        ), new Field(
                            "0,0,0,0;" +
                            "0,0,1,1;" +
                            "2,0,1,1"
                        )
                    ))
            }

            it("in a 4 by 3 grid with one block in the right corner, should return the correct states") {
                possibleEndLocationsFor(new Field(
                    "1,1,0,0;" +
                    "1,1,0,0;" +
                    "0,0,0,2"), O) should === (resultOf(
                        new Field(
                            "0,0,0,0;" +
                            "1,1,0,0;" +
                            "1,1,0,2"
                        ), new Field(
                            "0,0,0,0;" +
                            "0,1,1,0;" +
                            "0,1,1,2"
                        ), new Field(
                            "0,0,1,1;" +
                            "0,0,1,1;" +
                            "0,0,0,2"
                        )
                    ))
            }

            it("in a 4 by 4 grid with two blocks in the right corner, should return the correct states") {
                possibleEndLocationsFor(new Field(
                    "1,1,0,0;" +
                    "1,1,0,0;" +
                    "0,0,0,2;" +
                    "0,0,0,2"), O) should === (resultOf(
                        new Field(
                            "0,0,0,0;" +
                            "0,0,0,0;" +
                            "1,1,0,2;" +
                            "1,1,0,2"
                        ), new Field(
                            "0,0,0,0;" +
                            "0,0,0,0;" +
                            "0,1,1,2;" +
                            "0,1,1,2"
                        ), new Field(
                            "0,0,1,1;" +
                            "0,0,1,1;" +
                            "0,0,0,2;" +
                            "0,0,0,2"
                        )
                    ))
            }
        }
    }
}
