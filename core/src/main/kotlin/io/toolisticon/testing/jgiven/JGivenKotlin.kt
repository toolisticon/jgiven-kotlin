@file:Suppress("unused")

package io.toolisticon.testing.jgiven

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.base.ScenarioTestBase

/**
 * Use `GIVEN` instead of `given()` on Given-Stage "G"
 */
val <G : Stage<G>, W : Stage<W>, T : Stage<T>> ScenarioTestBase<G, W, T>.GIVEN: G get() = given()

/**
 * Use `GIVEN` instead of `given()` on Given-Stage "G", allowing you to write `GIVEN { ... }`
 */
fun <G : Stage<G>, W : Stage<W>, T : Stage<T>> ScenarioTestBase<G, W, T>.GIVEN(block: G.() -> G): G = GIVEN.block()

/**
 * Use `WHEN` instead of `when()` on When-Stage "W"
 */
val <G : Stage<G>, W : Stage<W>, T : Stage<T>> ScenarioTestBase<G, W, T>.WHEN: W get() = `when`()

/**
 * Use `WHEN` instead of `when()` on When-Stage "W", allowing you to write `WHEN { ... }`
 */
fun <G : Stage<G>, W : Stage<W>, T : Stage<T>> ScenarioTestBase<G, W, T>.WHEN(block: W.() -> W): W = WHEN.block()

/**
 * Use `THEN` instead of `then()` on Then-Stage "T"
 */
val <G : Stage<G>, W : Stage<W>, T : Stage<T>> ScenarioTestBase<G, W, T>.THEN: T get() = then()

/**
 * Use `THEN` instead of `then()` on Then-Stage "T", allowing you to write `THEN { ... }`
 */
fun <G : Stage<G>, W : Stage<W>, T : Stage<T>> ScenarioTestBase<G, W, T>.THEN(block: T.() -> T): T = THEN.block()

/**
 * Use `AND` instead of `and()`
 */
val <X : Stage<X>> Stage<X>.AND: X get() = and()

/**
 * Use `WITH` instead of `with()`
 */
val <X : Stage<X>> Stage<X>.WITH: X get() = with()

/**
 * Use `BUT` instead of `but()`
 */
val <X : Stage<X>> Stage<X>.BUT: X get() = but()

/**
 * Null safe access to `self()`. Prevents platform type warning
 */
val <X : Stage<X>> Stage<X>.self: X get() = self()!!

/**
 * Allows you to write step functions in Stages like this:
 *
 * ```
 * fun `something happens`(event:Event) = step {
 *    // your code
 * }
 * ```
 *
 * `step` is short for `self().apply {}` and returns the Stage instance for fluent usage.
 *
 */
inline fun <X : Stage<X>> Stage<X>.step(block: X.() -> Unit) = self.apply(block)
