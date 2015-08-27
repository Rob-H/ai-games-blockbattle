package bot

import field._
import moves._
import org.scalatest._

class PathFinderSpec extends FunSpec with Matchers {
    it("when you are already in the correct state, no moves are made") {
        val startField = Field(
            "1,1;" +
            "1,1"
        )

        val targetField = Field(
            "1,1;" +
            "1,1"
        )

        new Bot().getPathTo(new FieldInPlay(startField, Shape(O), Location(0,0)), targetField) shouldBe empty
    }

    it("when you just need to move right, one right move is made") {
        val startField = Field(
            "1,1,0;" +
            "1,1,0"
        )

        val targetField = Field(
            "0,1,1;" +
            "0,1,1"
        )

        new Bot().getPathTo(new FieldInPlay(startField, Shape(O), Location(0,0)), targetField) should === (List(RIGHT))
    }

    it("when you just need to move left, one left move is made") {
        val startField = Field(
            "0,1,1;" +
            "0,1,1"
        )

        val targetField = Field(
            "1,1,0;" +
            "1,1,0"
        )

        new Bot().getPathTo(new FieldInPlay(startField, Shape(O), Location(1,0)), targetField) should === (List(LEFT))
    }

    it("when you just need to drop, no moves are made") {
        val startField = Field(
            "1,1;" +
            "1,1;" +
            "0,0;" +
            "0,0;" +
            "0,0"
        )

        val targetField = Field(
            "0,0;" +
            "0,0;" +
            "0,0;" +
            "1,1;" +
            "1,1"
        )

        new Bot().getPathTo(new FieldInPlay(startField, Shape(O), Location(0,0)), targetField) shouldBe empty
    }
}

