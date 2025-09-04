package advanceKotlin.advanceFunctions

/**
 * # Anonymous Functions in Kotlin
 * 
 * This file demonstrates anonymous functions and their relationship with lambdas.
 * Shows different ways to define and use function expressions.
 * 
 * ## Topics Covered:
 * - **Anonymous Function**: Functions without names using `fun` keyword
 * - **Lambda Expression**: Concise function syntax with `{}`
 * - **Function Types**: Type declarations for function parameters
 * - **Function Conversion**: Converting between lambdas and anonymous functions
 * 
 * @author Udit
 * @since 1.0
 */

val isEven = fun (n: Int): Boolean { return n % 2 == 0 }
val isEvenConvertedToLambda: (Int)->Boolean = { a->  a % 2 == 0}
val greet: (String) -> String = { name -> "Hello, $name" }
val greetConvertedToAnonymous = fun(name: String): String { return "Hello, $name" }


fun main() {
    println(isEven(23))
    println(greet("MOTI"))
}











