package kClass

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