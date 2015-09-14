import org.scalameter.measure
import org.scalatest._
import bot._
import field._

class PerformanceSpec extends FunSpec with Matchers {
    describe("getting possible end states is performant") {
        it("for this scenario with a J and a T") {
            val field = Field(
                "0,0,0,1,1,1,0,0,0,0;" +
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
                "0,0,0,0,0,0,0,2,0,0;" +
                "0,0,0,0,0,0,0,2,2,0;" +
                "0,2,2,2,2,2,2,2,2,0;" +
                "2,2,0,2,2,2,2,2,2,2"
            )

            val time = measure {
                new Bot().getOrderedPossibleEndStates(field, J, T)
            }

            // scalastyle:off
            time.value.toInt should be < 500
        }
    }
}

