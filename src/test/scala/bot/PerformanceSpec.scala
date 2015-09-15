import org.scalameter._
import org.scalatest._
import bot._
import field._

class PerformanceSpec extends FunSpec with Matchers {
    private val timesToRun = 10
    private val acceptableMillisecondsToComplete = 400

    private def repeatMeasureWithWarming(block: => Unit) =
        config(Key.exec.benchRuns -> timesToRun) withWarmer { new Warmer.Default } measure { block }

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

            val time = repeatMeasureWithWarming {
                new Bot().getOrderedPossibleEndStates(field, J, T)
            }

            time.value.toInt should be <= acceptableMillisecondsToComplete
        }

        it("for this scenario with two S shapes") {
            val field = Field(
                "0,0,0,1,1,0,0,0,0,0;" +
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
                "0,2,2,0,0,0,0,0,0,0;" +
                "2,2,0,0,0,0,0,0,0,0"
            )

            val time = repeatMeasureWithWarming {
                new Bot().getOrderedPossibleEndStates(field, S, S)
            }

            time.value.toInt should be <= acceptableMillisecondsToComplete
        }
    }
}

