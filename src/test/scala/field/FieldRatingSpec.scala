package field
import org.scalatest._

class FieldRatingSpec extends FunSpec with Matchers {
    describe("a field with a full row vs one without") {
        it("for block in left corner and T shape, the full row should have a higher score") {
            val field1 = Field(
                "0,0,0,0;" +
                "0,0,0,0;" +
                "0,0,1,0;" +
                "2,1,1,1;"
            )
            val field2 = Field(
                "0,0,0,0;" +
                "0,0,0,0;" +
                "1,1,1,0;" +
                "2,1,0,0;"
            )
            field1.heuristic should be > field2.heuristic
        }

        it("for block in right corner and T shape, the full row should have a higher score") {
            val field1 = Field(
                "0,0,0,0;" +
                "0,0,0,0;" +
                "0,1,0,0;" +
                "1,1,1,2;"
            )
            val field2 = Field(
                "0,0,0,0;" +
                "0,0,0,0;" +
                "0,1,1,1;" +
                "0,0,1,2;"
            )
            field1.heuristic should be > field2.heuristic
        }
    }

    describe("a field with two full rows vs one with just one") {
        it("the two full rows should have a higher score") {
            val field1 = Field(
                "0,0,0,0;" +
                "0,0,0,0;" +
                "2,1,1,1;" +
                "2,2,1,2;"
            )
            val field2 = Field(
                "0,0,0,0;" +
                "0,0,1,0;" +
                "2,1,1,0;" +
                "2,2,1,2;"
            )
            field1.heuristic should be > field2.heuristic
        }
    }

    describe("a field where you cannot have a full row"){
        describe("should favour being closer to having full rows") {
            it("for squares in the left hand corner and a O block") {
                val field1 = Field(
                    "0,0,0,0,0;" +
                    "0,0,0,0,0;" +
                    "0,0,0,0,0;" +
                    "2,2,1,1,0;" +
                    "2,2,1,1,0;"
                )
                val field2 = Field(
                    "0,0,0,0,0;" +
                    "1,1,0,0,0;" +
                    "1,1,0,0,0;" +
                    "2,2,0,0,0;" +
                    "2,2,0,0,0;"
                )
                field1.heuristic should be > field2.heuristic
            }
            it("particularly near the bottom") {
                val field1 = Field(
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,2,2,0,0,0,0;" +
                    "0,0,2,2,2,2,0,0,0,0;" +
                    "0,0,2,2,2,2,0,0,0,0;" +
                    "0,0,2,2,2,2,0,0,0,0;" +
                    "0,0,2,2,2,2,0,0,2,0;" +
                    "0,2,2,2,2,2,0,2,2,0;" +
                    "0,0,2,2,2,2,0,2,2,0;" +
                    "0,2,2,2,2,2,0,2,2,0;" +
                    "0,2,2,2,2,2,0,2,2,0;" +
                    "1,2,2,2,2,0,0,2,2,0;" +
                    "1,2,2,2,2,2,0,2,2,0;" +
                    "1,2,2,2,2,2,2,2,2,0;" +
                    "1,2,2,2,2,2,2,2,2,0;" +
                    "2,0,2,2,2,2,2,2,2,2;" +
                    "3,3,3,3,3,3,3,3,3,3"
                )
                val field2 = Field(
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,0,0,0,0,0,0;" +
                    "0,0,0,0,2,2,0,0,1,0;" +
                    "0,0,2,2,2,2,0,0,1,0;" +
                    "0,0,2,2,2,2,0,0,1,0;" +
                    "0,0,2,2,2,2,0,0,1,0;" +
                    "0,0,2,2,2,2,0,0,2,0;" +
                    "0,2,2,2,2,2,0,2,2,0;" +
                    "0,0,2,2,2,2,0,2,2,0;" +
                    "0,2,2,2,2,2,0,2,2,0;" +
                    "0,2,2,2,2,2,0,2,2,0;" +
                    "0,2,2,2,2,0,0,2,2,0;" +
                    "0,2,2,2,2,2,0,2,2,0;" +
                    "0,2,2,2,2,2,2,2,2,0;" +
                    "0,2,2,2,2,2,2,2,2,0;" +
                    "2,0,2,2,2,2,2,2,2,2;" +
                    "3,3,3,3,3,3,3,3,3,3"
                )
                field1.heuristic should be > field2.heuristic
            }
        }

        describe("should prefer having groups of blocks together") {
            it("for this scenario") {
                val field1 = Field(
                    "0,0,0,0,0,0;" +
                    "0,0,0,0,0,0;" +
                    "0,0,0,0,0,0;" +
                    "2,2,1,1,0,0;" +
                    "2,2,1,1,0,0;"
                )
                val field2 = Field(
                    "0,0,0,0,0,0;" +
                    "0,0,0,0,0,0;" +
                    "0,0,0,0,0,0;" +
                    "2,2,0,1,1,0;" +
                    "2,2,0,1,1,0;"
                )
                field1.heuristic should be > field2.heuristic
            }

            it("when there is a space at the start") {
                val field1 = Field(
                    "0,0,0,0,0,0;" +
                    "0,0,0,0,0,0;" +
                    "0,0,0,0,0,0;" +
                    "1,1,2,2,0,0;" +
                    "1,1,2,2,0,0;"
                )
                val field2 = Field(
                    "0,0,0,0,0,0;" +
                    "0,0,0,0,0,0;" +
                    "0,0,0,0,0,0;" +
                    "0,1,1,2,2,0;" +
                    "0,1,1,2,2,0;"
                )
                field1.heuristic should be > field2.heuristic

            }
        }
    }
}

