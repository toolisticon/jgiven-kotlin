package io.toolisticon.testing.jgiven

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ScenarioState
import com.tngtech.jgiven.annotation.ScenarioState.Resolution.NAME
import com.tngtech.jgiven.junit5.SimpleScenarioTest
import io.toolisticon.testing.jgiven.format.VarargsQuoted
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

  @Test
  fun `calculator adds two numbers as varargs`() {
    GIVEN
      .`numbers are $`(5, 4)

    WHEN
      .`both numbers are added`()

    THEN
      .`the sum is $`(9)
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

  /**
   * Sets both numbers via vararg, use to verify Vararg formatter.
   */
  fun `numbers are $`(@VarargsQuoted vararg nums: Int) = step {
    require(nums.size == 2) { "need to pass two numbers" }
    `the first number is $`(nums[0]).`the second number is $`(nums[1])
  }

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
