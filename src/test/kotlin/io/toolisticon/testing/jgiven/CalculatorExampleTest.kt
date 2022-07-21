package io.toolisticon.testing.jgiven

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ScenarioState
import com.tngtech.jgiven.annotation.ScenarioState.Resolution.NAME
import com.tngtech.jgiven.junit5.SimpleScenarioTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Simple example using GIVEN/WHEN/THEN extensions, the step() lambda and the all open annotation.
 */
internal class CalculatorExampleTest : SimpleScenarioTest<CalculatorStage>() {

  @Test
  fun `calculator adds two numbers`() {
    GIVEN
      .`the first number is $`(4)
      .AND
      .`the second number is $`(7)

    WHEN
      .`both numbers are added`()

    THEN
      .`the sum is $`(11)
  }
}

@JGivenKotlinStage
class CalculatorStage : Stage<CalculatorStage>() {

  @ScenarioState(resolution = NAME)
  var firstNumber: Int = 0

  @ScenarioState(resolution = NAME)
  var secondNumber: Int = 0

  @ScenarioState(resolution = NAME)
  var sum: Int = 0

  fun `the first number is $`(n: Int) = step {
    this.firstNumber = n
  }

  fun `the second number is $`(n: Int) = step {
    this.secondNumber = n
  }

  fun `both numbers are added`() = step {
    sum = firstNumber + secondNumber
  }

  fun `the sum is $`(expected: Int) = step {
    assertEquals(expected, sum)
  }

}
