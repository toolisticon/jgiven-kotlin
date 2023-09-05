package io.toolisticon.testing.jgiven.format

import io.toolisticon.testing.jgiven.format.VarargsFormatter.Companion.anyToList
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


internal class VarargsFormatterTest {

  private var formatter: VarargsFormatter = VarargsFormatter()


  @Test
  fun `format a single value`() {
    assertThat(formatter.format("value", "\"%s\"")).isEqualTo("\"value\"")
  }

  @Test
  fun `format single array values`() {
    val varargs = arrayOf("value")
    assertThat(formatter.format(varargs, "\"%s\"")).isEqualTo("\"value\"")
  }

  @Test
  fun `format multiple array values`() {
    val varargs = arrayOf<Any>("value1", 1L)
    assertThat(formatter.format(varargs, "\"%s\"")).isEqualTo("\"value1\", \"1\"")
  }

  @Test
  fun `fail without formatterArguments`() {
    val varargs = arrayOf("value")

    assertThatThrownBy { formatter.format(varargs) }
      .isInstanceOf(ArrayIndexOutOfBoundsException::class.java)
  }

  @Test
  fun `format null value`() {
    val varargs: Array<String>? = null
    assertThat(formatter.format(varargs, "\"%s\"")).isEqualTo("\"null\"")
  }

  @Nested
  inner class AnyToList {


    @Test
    fun `null to null`() {
      assertThat(anyToList(null)).isNull()
    }

    @Test
    fun `non-array to null`() {
      assertThat(anyToList("")).isNull()
    }

    @Test
    fun `int to list`() {
      val list: List<Any?> = requireNotNull(anyToList(varargInt(1, 2, 3)))

      assertThat(list).containsExactly(1, 2, 3)
    }

    @Test
    fun `boolean to list`() {
      val list = requireNotNull(anyToList(varargBoolean(true, false, null)))

      assertThat(list).containsExactly(true, false, null)
    }
  }

  private fun varargInt(vararg v: Int): Any? = v
  private fun varargBoolean(vararg v: Boolean?): Any? = v
}
