package advanceKotlin.advanceClasses

import java.util.Stack

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