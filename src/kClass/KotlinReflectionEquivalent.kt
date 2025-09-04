package kClass

/**
 * # Kotlin Reflection and KClass
 * 
 * This file demonstrates Kotlin reflection capabilities and KClass usage.
 * Shows how to inspect and manipulate code at runtime.
 * 
 * ## Topics Covered:
 * - **KClass**: Kotlin's reflection API for class information
 * - **Runtime Inspection**: Examining classes, properties, and functions at runtime
 * - **Reflection API**: Kotlin's reflection capabilities
 * - **Type Information**: Getting type information dynamically
 * - **Metadata Access**: Accessing class metadata and annotations
 * 
 * @author Udit
 * @since 1.0
 */

import kotlin.reflect.KClass

fun main() {
    val kClass: KClass<KotlinKClass> = KotlinKClass::class
    for(property in kClass.members) {
        println(property.name)
    }
}

class KotlinKClass(private val age: Int, private val ids: String) {
    fun showAge(): Int = age + 1
    private fun showId(): String = ids
}


class Number(val value: Int) {
    infix fun add(other: Number): Int {
        return this.value + other.value
    }
}

val a = Number(1)
val b = Number(2)
val sum  = a add b

val name: String by lazy {
    "Udit"
}