package bot;

import filed._
import org.scalatest._

class PossibleEndStatesSpec extends FunSpec with Matchers { 
    describe("for shape 0") {
        describe("and an empty field") {
            it("that is only as big as an O, should return correct state") {
                new Bot().getPossibleEndLocations(new Field(2, 2, "1,1;1,1"), O) should === (List(Location(0,0)))
            }

            it("that is 3 by 2, should return correct states") {
                new Bot().getPossibleEndLocations(new Field(3, 2, "1,1,0;1,1,0"), O) should === (List(Location(0,0), Location(1,0)))
            }

            it("that is 4 by 2, should return correct states") {
                new Bot().getPossibleEndLocations(new Field(4, 2, "1,1,0,0;1,1,0,0"), O) should === (List(Location(0,0), Location(1,0), Location(2,0)))
            }

            it("that is 4 by 3, should return correct states") {
                new Bot().getPossibleEndLocations(new Field(4, 3, "1,1,0,0;1,1,0,0;0,0,0,0"), O) should === (List(Location(0,1), Location(1,1), Location(2,1)))
            }
        }

        describe("a field with blocks all along the bottom") {
            it("in a 4 by 3 grid, should return the correct states") {
                new Bot().getPossibleEndLocations(new Field(4, 3, "1,1,0,0;1,1,0,0;2,2,2,2"), O) should === (List(Location(0,0), Location(1,0), Location(2,0)))
            }
        }

        describe("a field with solids all along the bottom") {
            it("in a 4 by 3 grid, should return the correct states") {
                new Bot().getPossibleEndLocations(new Field(4, 3, "1,1,0,0;1,1,0,0;3,3,3,3"), O) should === (List(Location(0,0), Location(1,0), Location(2,0)))
            }
        }

        describe("a field with uneven blocks") {
            it("in a 4 by 3 grid with one block in the corner, should return the correct states") {
                new Bot().getPossibleEndLocations(new Field(4, 3, "1,1,0,0;1,1,0,0;2,0,0,0"), O) should === (List(Location(0,0), Location(1,1), Location(2,1)))
            }

        }
    }
}
