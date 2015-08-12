package bot;
import filed._
import org.scalatest._

class PossibleEndStatesSpec extends FlatSpec with Matchers { 
    it should "return correct state for shape O and empty board that is only as big as an O" in {
        new Bot().getPossibleEndLocations(new Field(2, 2, "1,1;1,1"), O, Location(0,0)) should === (List(Location(0,0)))
        
    }
}
