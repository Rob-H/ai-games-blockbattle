package bot;

import filed._
import org.scalatest._

class PossibleEndStatesSpec extends FunSpec with Matchers { 
    describe("for shape 0") {
        describe("and an empty board") {
            it("that is only as big as an O, should return correct state") {
                new Bot().getPossibleEndLocations(new Field(2, 2, "1,1;1,1"), O, Location(0,0)) should === (List(Location(0,0)))
            }

            it("that is  3 by 2, should return correct states") {
                new Bot().getPossibleEndLocations(new Field(3, 2, "1,1,0;1,1,0"), O, Location(0,0)) should === (List(Location(0,0), Location(1,0)))
            }
        }
    }

}
