package bot

import field._
import org.scalatest._

class DelayedGratificationSpec extends FunSpec with Matchers {
    describe("when you take in to consideration the next shape, filling a line is not the best thing to do") {
        it("will not fill the line straight away") {
            val field = Field(
                "0,0,0,0,0,0;" +
                "0,0,0,0,0,0;" +
                "0,0,0,0,0,0;" +
                "0,0,0,0,0,0;" +
                "2,2,2,2,0,0;" +
                "2,2,2,2,0,0;" +
                "2,2,2,2,0,0;" +
                "2,2,0,0,0,0"
            )

            new Bot().getOrderedPossibleEndStates(field, I, I).head should === (Field(
                "0,0,0,0,0,0;" +
                "0,0,0,0,0,0;" +
                "0,0,0,0,0,0;" +
                "0,0,0,0,0,0;" +
                "2,2,2,2,1,0;" +
                "2,2,2,2,1,0;" +
                "2,2,2,2,1,0;" +
                "2,2,0,0,1,0"
            ))
        }
    }

    describe("when the next good state is actually unreachable") {
        it("does not use it") {
            val field = Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,2,0,0,0;" +
                "2,2,0,0,0;" +
                "2,0,0,0,0"
            )
            new Bot().getOrderedPossibleEndStates(field, S, T).head should !== (Field(
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,0,0,0,0;" +
                "0,2,0,1,0;" +
                "2,2,0,1,1;" +
                "2,0,0,0,1"
            ))
        }
    }

    describe("specific scenarios") {
        it("with an S then an I") {
            val field = Field(
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,2,0,0,0,0;" +
                "0,0,0,0,2,2,2,0,2,0;" +
                "0,2,2,0,2,2,2,0,2,2;" +
                "2,2,2,0,2,2,2,2,2,2;" +
                "2,2,2,0,2,2,2,2,2,2"
            )
            new Bot().getOrderedPossibleEndStates(field, S, I).head should === (Field(
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,1,0;" +
                "0,0,0,0,0,2,0,0,1,1;" +
                "0,0,0,0,2,2,2,0,2,1;" +
                "0,2,2,0,2,2,2,0,2,2;" +
                "2,2,2,0,2,2,2,2,2,2;" +
                "2,2,2,0,2,2,2,2,2,2"
            ))
        }
    }
}

