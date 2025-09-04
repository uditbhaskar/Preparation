package learnBasicsKotlin

/**
 * # Variables and Type System in Kotlin
 * 
 * This file demonstrates Kotlin's type system, smart casting, and variable declarations.
 * Shows fundamental concepts of Kotlin's type safety and null handling.
 * 
 * ## Topics Covered:
 * - **Type Checking**: Using `is` operator for runtime type checks
 * - **Smart Casting**: Automatic casting after type checks
 * - **Exception Handling**: Custom exceptions and error handling
 * - **String Templates**: Multi-line strings and formatting
 * - **Null Safety**: Safe calls and type casting
 * 
 * @author Udit
 * @since 1.0
 */

fun printObj(obj: Any) {
    if (obj is String) {
        println("$obj is String")
    }
    if(obj is Float){
        println("$obj is Float")
    }
    else {
        throw ObjectNotRecognisedException("Object not recognised")
    }
}

class ObjectNotRecognisedException(msg: String): Exception(msg)

fun main() {
    printObj(9.0f)
    val str = """what rto 
        |
        |
        |sdnindd
        |ekneie
        |wkidnwidn
    """.trimMargin()
    println(str)

    testSmartCastFailure(null)
}


fun testSmartCastFailure(str: String?) {
    val obj: Any? = str
    if (obj != null && obj is String) {
        println(obj.length)
    }
    var newObj: Any? = str
    val safeStr: String? = newObj as? String
    println(safeStr)
}