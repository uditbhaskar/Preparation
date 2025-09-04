package learnBasicsKotlin

/**
 * # Lambda Functions - Basic Concepts
 * 
 * This file demonstrates basic lambda function concepts in Kotlin.
 * Shows fundamental lambda syntax and usage patterns.
 * 
 * ## Topics Covered:
 * - **Lambda Syntax**: Basic { parameter -> body } syntax
 * - **Function Types**: Type declarations for lambda parameters
 * - **Higher-Order Functions**: Functions that accept lambdas
 * - **Currying**: Functions returning other functions
 * - **Closures**: Accessing variables from enclosing scope
 * 
 * @author Udit
 * @since 1.0
 */

var sumTwoNum: (Int) -> (Int) -> Int = { a -> { i -> a + i } }

fun main() {
    println(sumTwoNum(11).invoke(45))
    println(operate(30, 20) { a, b ->
        a + b
    })
}


fun operate(a: Int, b: Int, operation: (x: Int, y: Int) -> Int): Int = operation(a, b)