package advanceKotlin.advanceClasses

import java.util.Stack

/**
 * # Understanding Backing Fields in Kotlin
 * 
 * This file demonstrates backing fields and custom getters/setters in Kotlin.
 * Shows how properties work internally and how to customize their behavior.
 * 
 * ## Topics Covered:
 * - **Backing Field**: The `field` identifier in custom getters/setters
 * - **Custom Getters**: Custom logic when accessing properties
 * - **Custom Setters**: Custom logic when setting properties
 * - **Property Validation**: Using setters to validate input
 * 
 * @author Udit
 * @since 1.0
 */

data class Student(
    val id: Int,
    var age: Int,
    var name: String
) {
    var isSenior: Boolean = true
        get() = age > 14
        set(value) = if(value) field = age > 14 else field = value
    var s = listOf(1, 1)
        get() = listOf()
        set(value) {
            field = listOf()
        }
}


/**
 * data class Student(
 *     val id: Int,
 *     var name: String,
 * ) {
 *     var age: Int = 0
 *         set(value) {
 *             field = if (value < 0) {
 *                 println("Invalid age! Setting to 0")
 *                 0
 *             } else {
 *                 value
 *             }
 *         }
 *
 *     constructor(id: Int, name: String, age: Int) : this(id, name) {
 *         this.age = age // Triggers custom setter
 *     }
 *
 *     val isSenior: Boolean
 *         get() = age > 14
 * }
 *
 */

fun main(){
    val newStudent = Student(123, 12, "XYZ")
    println("The student is senior: ${newStudent.isSenior}")
    newStudent.age = 15
    println("The student is senior: ${newStudent.isSenior}")
}