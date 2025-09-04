package advanceKotlin.advanceFunctions

/**
 * # Infix Functions in Kotlin
 * 
 * This file demonstrates infix functions and their usage.
 * Shows how to create more readable function calls using infix notation.
 * 
 * ## Topics Covered:
 * - **Infix Functions**: Functions called with infix notation (no dots or parentheses)
 * - **Extension Functions**: Adding functions to existing classes
 * - **Member Functions**: Functions defined inside classes
 * - **Readable DSL**: Creating domain-specific language-like syntax
 * 
 * @author Udit
 * @since 1.0
 */

class Miami {
        infix fun hasBesBeaches(isBest: Boolean) {
            println("The statement is $isBest that Miami has best beaches in the world.")
        }


    }

    fun main() {
    val obj = Miami()
    obj hasBesBeaches true
    obj isBeautiful true
    println(23 getAverage 4.0f)
    println(obj)

    val n = normal(23)
    println(n)
}

    infix fun Miami.isBeautiful(isBeautiful: Boolean) =
        println("The statement is $isBeautiful that Miami is very beautiful.")


    infix fun Int.getAverage(totalSubjects: Float): Float {
        return this / totalSubjects
    }



data class normal(val c:Int)