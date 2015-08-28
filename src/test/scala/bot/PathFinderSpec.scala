package bot

import field._
import moves._
import org.scalatest._

class PathFinderSpec extends FunSpec with Matchers {
    describe("the path finding function") {
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

        describe("when you just need to drop, no moves are made") {
            it("for a 2 by 5 grid") {
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

            it("for a 2 by 6 grid") {
                val startField = Field(
                    "1,1;" +
                    "1,1;" +
                    "0,0;" +
                    "0,0;" +
                    "0,0;" +
                    "0,0"
                )

                val targetField = Field(
                    "0,0;" +
                    "0,0;" +
                    "0,0;" +
                    "0,0;" +
                    "1,1;" +
                    "1,1"
                )

                new Bot().getPathTo(new FieldInPlay(startField, Shape(O), Location(0,0)), targetField) shouldBe empty

            }
        }

        it("when you need to go right twice, two right moves are made") {
             val startField = Field(
                "1,1,0,0;" +
                "1,1,0,0"
            )

            val targetField = Field(
                "0,0,1,1;" +
                "0,0,1,1"
            )

            new Bot().getPathTo(new FieldInPlay(startField, Shape(O), Location(0,0)), targetField) should === (List(RIGHT, RIGHT))
        }

        it("when you need to go left twice two left moves are made") {
            val startField = Field(
                "0,0,1,1;" +
                "0,0,1,1"
            )

            val targetField = Field(
                "1,1,0,0;" +
                "1,1,0,0"
            )

            new Bot().getPathTo(new FieldInPlay(startField, Shape(O), Location(2,0)), targetField) should === (List(LEFT, LEFT))
        }

        it("when you need to go right 5 times, five right moves are made") {
             val startField = Field(
                "1,1,0,0,0,0,0;" +
                "1,1,0,0,0,0,0"
            )

            val targetField = Field(
                "0,0,0,0,0,1,1;" +
                "0,0,0,0,0,1,1"
            )

            new Bot().getPathTo(new FieldInPlay(startField, Shape(O), Location(0,0)), targetField) should === (List(RIGHT, RIGHT, RIGHT, RIGHT, RIGHT))
        }
    }
}

