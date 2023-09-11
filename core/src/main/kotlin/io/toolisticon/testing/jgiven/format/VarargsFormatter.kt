package io.toolisticon.testing.jgiven.format

import com.tngtech.jgiven.annotation.Format
import com.tngtech.jgiven.format.ArgumentFormatter
import com.tngtech.jgiven.format.PrintfFormatter
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*


/**
 * Varargs step parameters annotated with this annotation will be put into quotes (" ") in reports.
 */
@MustBeDocumented
@Format(value = VarargsFormatter::class, args = ["\"%s\""])
@Target(FIELD, VALUE_PARAMETER, ANNOTATION_CLASS)
@Retention(RUNTIME)
annotation class VarargsQuoted

/**
 * Argument formatter for varargs delegating to [PrintfFormatter]
 */
class VarargsFormatter @JvmOverloads constructor(private val delimiter: String = ", ") : ArgumentFormatter<Any?> {
  companion object {
    fun anyToList(instance: Any?): List<Any?>? {
      if (instance == null || !instance::class.java.isArray) {
        return null
      }

      // deal with the various primitive array (int[],...) wrappers in kotlin.
      return when (instance) {
        is IntArray -> instance.toList()
        is ByteArray -> instance.toList()
        is CharArray -> instance.toList()
        is ShortArray -> instance.toList()
        is LongArray -> instance.toList()
        is DoubleArray -> instance.toList()
        is FloatArray -> instance.toList()
        is BooleanArray -> instance.toList()
        else -> (instance as Array<*>).toList()
      }
    }
  }

  private val formatter = PrintfFormatter()

  override fun format(argumentToFormat: Any?, vararg formatterArguments: String): String {
    val argumentList = anyToList(argumentToFormat)

    return argumentList
      ?.joinToString(separator = delimiter) { formatter.format(it, *formatterArguments) }
      ?: formatter.format(argumentToFormat, *formatterArguments)
  }
}
