package bot

import field._
import moves._
import org.scalatest._

class PathFinderSpec extends FunSpec with Matchers {
    private def getPathTo(targetField: Field, shape: Shape, shapeStartLocation: Location) =
        new Bot().getPathTo(new FieldInPlay(targetField, shape, shapeStartLocation), targetField)
    private def resultOf(moves: MoveType*) = Some(moves.toList)

    describe("the path finding function") {
        it("when you are already in the correct state, no moves are made") {
            val targetField = Field(
                "1,1;" +
                "1,1"
            )

            getPathTo(targetField, Shape(O), Location(0,0)) should === (resultOf())
        }

        it("when you just need to move right, one right move is made") {
            val targetField = Field(
                "0,1,1;" +
                "0,1,1"
            )
            getPathTo(targetField, Shape(O), Location(0,0)) should === (resultOf(RIGHT))
        }

        it("when you just need to move left, one left move is made") {
            val targetField = Field(
                "1,1,0;" +
                "1,1,0"
            )

            getPathTo(targetField, Shape(O), Location(1,0)) should === (resultOf(LEFT))
        }

        describe("when you just need to drop, no moves are made") {
            it("for a 2 by 5 grid") {
                val targetField = Field(
                    "0,0;" +
                    "0,0;" +
                    "0,0;" +
                    "1,1;" +
                    "1,1"
                )
                getPathTo(targetField, Shape(O), Location(0,0)) should === (resultOf())
            }

            it("for a 2 by 6 grid") {
                val targetField = Field(
                    "0,0;" +
                    "0,0;" +
                    "0,0;" +
                    "0,0;" +
                    "1,1;" +
                    "1,1"
                )

                getPathTo(targetField, Shape(O), Location(0,0)) should === (resultOf())

            }
        }

        it("when you need to go right twice, two right moves are made") {
            val targetField = Field(
                "0,0,1,1;" +
                "0,0,1,1"
            )

            getPathTo(targetField, Shape(O), Location(0,0)) should === (resultOf(RIGHT, RIGHT))
        }

        it("when you need to go left twice two left moves are made") {
            val targetField = Field(
                "1,1,0,0;" +
                "1,1,0,0"
            )

            getPathTo(targetField, Shape(O), Location(2,0)) should === (resultOf(LEFT, LEFT))
        }

        it("when you need to go right 5 times, five right moves are made") {
            val targetField = Field(
                "0,0,0,0,0,1,1;" +
                "0,0,0,0,0,1,1"
            )

            getPathTo(targetField, Shape(O), Location(0,0)) should === (resultOf(RIGHT, RIGHT, RIGHT, RIGHT, RIGHT))
        }

        it("when you need to go left 6 times, 6 left moves are made") {
            val targetField = Field(
                "1,1,0,0,0,0,0,0;" +
                "1,1,0,0,0,0,0,0"
            )

            getPathTo(targetField, Shape(O), Location(6,0)) should === (resultOf(LEFT, LEFT, LEFT, LEFT, LEFT, LEFT))
        }
    }
}

