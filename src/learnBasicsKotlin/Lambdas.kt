package learnBasicsKotlin

import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Operation

/**
 * A lambda in Kotlin is an anonymous function that can be treated as a
 * value—passed as an argument, returned from a function, or stored in a variable.
 * Lambdas are commonly used for concise function expressions,
 * especially in higher-order functions like map, filter, or custom callbacks.
 *
 * Key points:
 * Syntax: { parameter(s) -> body }
 * No explicit function name: Lambdas are unnamed and often used inline.
 * Can access variables from the enclosing scope (closures).
 * Commonly used for event listeners, collection operations, and functional programming patterns.
 */

var sumTwoNum: (Int) -> (Int) -> Int = { a -> { i -> a + i } }

fun main() {
    println(sumTwoNum(11).invoke(45))
    println(operate(30, 20) { a, b ->
        a + b
    })
}


fun operate(a: Int, b: Int, operation: (x: Int, y: Int) -> Int): Int {
    return operation(a, b)
}