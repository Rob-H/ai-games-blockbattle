package bot

import field._
import org.scalatest._

class PathFinderSpec extends FunSpec with Matchers {
    it("when you are already in the correct state, no moves are made") {
        val startField = Field(
            "1,1" +
            "1,1"
        )

        val targetField = Field(
            "1,1" +
            "1,1"
        )

        new Bot().getPathTo(new FieldInPlay(startField, Shape(O), Location(0,0)), targetField) shouldBe empty
    }
}

