package advanceKotlin.advanceFunctions

/**
 * # Lambda Functions in Kotlin
 * 
 * This file demonstrates lambda functions and higher-order functions.
 * Shows practical usage of lambdas in functional programming patterns.
 * 
 * ## Topics Covered:
 * - **Lambda Functions**: Anonymous function expressions
 * - **Higher-Order Functions**: Functions that take other functions as parameters
 * - **Function Types**: Type declarations for function parameters
 * - **Lambda with Receiver**: Lambda functions with implicit receiver objects
 * - **Collection Operations**: Using lambdas with map, filter, forEach
 * 
 * @author Udit
 * @since 1.0
 */

fun lambdaFunctionExample(
    isSunshine: () -> Boolean, isWindy: (Boolean) -> String, anotherLambda: () -> Unit
): (Int, Int) -> Int {
    isSunshine()
    println(isWindy(true))
    anotherLambda()
    return { first, second ->
        first + second
    }
}


fun addTwoNum(a: Int, b: Int, value: (Int, Int) -> Int): Int = value(a, b)

fun main() {
    println(
        addTwoNum(
            a= 5, b = 3, value  = lambdaFunctionExample(isSunshine = {
                println("This is Shiny.")
                true
            }, isWindy = { isWindy ->
                return@lambdaFunctionExample "It's $isWindy that it is windy today."
            }, anotherLambda = {
                println("Inside last Lambda")
                return@lambdaFunctionExample
            })

        )
    )

    val numbers = listOf(1, 2, 3, 4)
    val doubled = numbers.map(transform = { value ->
        (value+2).toString()
    })
    println(numbers)
    val evens = numbers.filter(predicate = { value -> value % 2 == 0 })
    println(evens)
    println(numbers)
    numbers.forEach(action = { num -> println(num) })
}




