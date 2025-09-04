package advanceKotlin.advanceFunctions

/**
 * # Operator Overloading in Kotlin
 * 
 * This file demonstrates operator overloading in Kotlin.
 * Shows how to customize behavior of standard operators for custom classes.
 * 
 * ## Topics Covered:
 * - **Operator Overloading**: Customizing behavior of +, -, *, etc. for custom classes
 * - **Unary Operators**: Overloading unary plus, minus operators
 * - **Binary Operators**: Overloading plus, times, etc. operators
 * - **Invoke Operator**: Making objects callable like functions
 * - **Operator Functions**: Special function names that map to operators
 * 
 * @author Udit
 * @since 1.0
 */

data class Coordinates(val x: Int, val y: Int, val z: Int) {
    operator fun unaryMinus(): Coordinates {
        return Coordinates(-x, -y, -z)
    }

    operator fun unaryPlus() {
        println("nhi krene dungaa")
    }

    operator fun invoke() {
        println("i wont let you do anything")
    }

    operator fun plus(obj: Coordinates) {
        println("nhi krene dungaa")
    }

    operator fun times(obj: Coordinates) {
        println("We can modify multiply as well")
    }
}

fun main() {
    val obj = Coordinates(23, 78, 90)
    obj()
    obj + obj
    obj * obj
    println(-obj)
    +obj
    obj * obj
}