package learnBasicsKotlin

/**
 * # Extension Functions in Kotlin
 * 
 * This file demonstrates extension functions in Kotlin.
 * Shows how to add new functionality to existing classes without inheritance.
 * 
 * ## Topics Covered:
 * - **Extension Functions**: Adding functions to existing classes
 * - **Receiver Type**: The class being extended
 * - **Null Safety**: Extension functions with nullable types
 * - **Scope Functions**: How extensions work with scope
 * - **Standard Library**: How Kotlin's standard library uses extensions
 * 
 * @author Udit
 * @since 1.0
 */

fun Int.addOne(): Int{
    return this + 1
}

fun String.capitalizeFirstChar(): String{
    try {
        val builder = this.toCharArray()
        builder[0] = builder[0].uppercaseChar()
        return String(builder)
    } catch (e: Exception) {
        return this
    }
}
fun main() {
    println(3.addOne())
    val z:String? = "aloha is mine"
    println(z?.capitalizeFirstChar())
}