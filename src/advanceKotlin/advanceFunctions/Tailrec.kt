package advanceKotlin.advanceFunctions

/**
 * # Tail Recursion in Kotlin
 * 
 * This file demonstrates tail recursion optimization in Kotlin.
 * Shows the difference between regular recursion and tail recursion.
 * 
 * ## Topics Covered:
 * - **Tail Recursion**: Recursion where the recursive call is the last operation
 * - **tailrec Modifier**: Kotlin keyword for tail recursion optimization
 * - **Stack Optimization**: How tail recursion prevents stack overflow
 * - **Recursion Patterns**: Different ways to implement recursive functions
 * - **Performance**: Memory efficiency of tail recursive functions
 * 
 * @author Udit
 * @since 1.0
 */
tailrec fun factorial(num: Int, total: Int = 1): Int {
    if (num < 2) return total
    val totalCalculation = num * total

    return factorial(num - 1, totalCalculation)
}

tailrec fun fact(num: Int): Int {
    if (num <= 1) return 1
    return fact(num - 1)
}

/**
 * This one gives warning because it doesn't follow last step to be just recursive, but it has another operation too. Hence, it can't be optimized as tail recursion
 */
tailrec fun factorialNonTailRec(num: Int): Int {
    if (num < 2) return 1
    return num * factorialNonTailRec(num - 1)
}


fun main() {
    println(factorial(5))
    println(fact(5))
}