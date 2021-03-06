package bot

import field._
import moves._
import org.scalatest._

class PathFinderSpec extends FunSpec with Matchers {
    private def getPathTo(targetField: Field, shape: Shape, shapeStartLocation: Location) = {
        val potentialFieldInPlays = for {
            x <- -2 until targetField.width
            y <- -2 until targetField.height
            rotatedShape <- shape.shapeType.uniqueRotations.map(Shape(shape.shapeType,_))
            if(rotatedShape.canBePlacedIn(targetField, Location(x,y)) && targetField.withShapeAt(rotatedShape, Location(x,y)) == targetField)
        } yield new FieldInPlay(targetField, rotatedShape, Location(x,y))

        new Bot().getPathTo(new FieldInPlay(targetField, shape, shapeStartLocation), potentialFieldInPlays.head)
    }
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

        it("when you need to move down and right, it can handle it") {
            val targetField = Field(
                "0,0,2,2;" +
                "0,1,1,2;" +
                "0,1,1,2"
            )
            getPathTo(targetField, Shape(O), Location(0,0)) should === (resultOf(DOWN, RIGHT))
        }

        it("when you need to turn right, it just turns right once") {
            val targetField = Field(
                "0,0,1,0;" +
                "0,0,1,0;" +
                "0,0,1,0;" +
                "0,0,1,0"
            )
            getPathTo(targetField, Shape(I), Location(0,0)) should === (resultOf(TURNRIGHT))
        }

        it("when you need to turn left, it just turns left once") {
            val targetField = Field(
                "0,1,0,0;" +
                "0,1,0,0;" +
                "0,1,0,0;" +
                "2,1,2,2"
            )
            getPathTo(targetField, Shape(I), Location(0,0)) should === (resultOf(TURNLEFT))
        }

        it("when the block starts above the field, it can handle it") {
            val targetField = Field(
                "0,0,0,0;" +
                "0,0,0,0;" +
                "1,1,0,0;" +
                "1,1,0,0"
            )
            getPathTo(targetField, Shape(O), Location(0,-2)) should === (resultOf())
        }

        it("works for a larger field") {
            val targetField = Field(
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "0,0,0,0,0,0,0,0,0,0;" +
                "1,1,0,0,0,0,0,0,0,0;" +
                "0,1,1,0,0,0,0,0,0,0"
            )

            getPathTo(targetField, Shape(Z), Location(3, -1)) should not be empty
        }

        describe("always tries to rotate T peice in to position if it can") {
            it("for a simple scenario") {
                val targetField = Field(
                    "0,0,1,0,0;" +
                    "0,0,1,1,0;" +
                    "0,0,1,0,0"
                )
                getPathTo(targetField, Shape(T), Location(0, 0)) should === (resultOf(RIGHT, TURNRIGHT))
            }

            it("when it can be dropped") {
                val targetField = Field(
                    "0,0,0,0;" +
                    "0,0,0,0;" +
                    "0,0,0,0;" +
                    "0,0,1,0;" +
                    "0,0,1,1;" +
                    "0,0,1,0"
                )
                getPathTo(targetField, Shape(T), Location(0, 0)) should === (resultOf(RIGHT, DOWN, DOWN, DOWN, TURNRIGHT))
            }
        }
    }
}

