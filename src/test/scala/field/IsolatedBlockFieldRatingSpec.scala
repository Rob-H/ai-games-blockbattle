package field
import org.scalatest._

class IsolatedBlockFieldRatingSpec extends FunSpec with Matchers {
    describe("should favour not having more full rows above less full rows") {
        it("for squares in the left corner and T block") {
            val field1 = Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,2,0,1,0;" +
                "0,2,1,1,1;"
            )
            val field2 = Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,2,1,1,1;" +
                "0,2,0,1,0;"
            )
            field1.heuristic should be > field2.heuristic
        }

        it("unless you're creating a full row") {
            val field1 = Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "2,2,1,1,1;" +
                "0,2,0,1,0;"
            )
            val field2 = Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "2,2,0,1,0;" +
                "0,2,1,1,1;"
            )
            field1.heuristic should be > field2.heuristic
        }

        it("with an I shape") {
             val field1 = Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "1,0,0,0,0;" +
                "1,0,0,0,0;" +
                "1,0,0,0,0;" +
                "1,2,0,2,0;"
            )
            val field2 = Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "1,1,1,1,0;" +
                "0,2,0,2,0;"
            )
            field1.heuristic should be > field2.heuristic
        }

        it("with a O shape") {
            val field1 = Field(
               "0,0,0,0,0,0,0,0,0,0;" +
               "0,0,0,0,0,0,0,0,0,0;" +
               "0,0,0,0,0,0,0,0,0,0;" +
               "0,0,0,0,0,0,0,0,0,0;" +
               "1,1,0,0,0,0,0,0,0,0;" +
               "1,1,2,0,0,0,0,0,0,0;" +
               "2,2,2,2,2,0,2,2,0,0;" +
               "0,2,2,2,2,2,2,2,2,0"
            )
            val field2 = Field(
               "0,0,0,0,0,0,0,0,0,0;" +
               "0,0,0,0,0,0,0,0,0,0;" +
               "0,0,0,0,0,0,0,0,0,0;" +
               "0,0,0,0,0,0,0,0,0,0;" +
               "0,0,0,0,0,0,0,0,0,0;" +
               "0,0,2,0,0,0,0,0,1,1;" +
               "2,2,2,2,2,0,2,2,1,1;" +
               "0,2,2,2,2,2,2,2,2,0"
            )
            field1.heuristic should be > field2.heuristic
        }

        it("unless you are going to above half way of the field") {
            val field1 = Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "1,1,1,1,0;" +
                "0,2,0,2,0;"
            )
            val field2 = Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "1,0,0,0,0;" +
                "1,0,0,0,0;" +
                "1,0,0,0,0;" +
                "1,2,0,2,0;"
            )
            field1.heuristic should be > field2.heuristic
        }
    }
}

