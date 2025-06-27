package advanceKotlin.advanceFunctions

/**
 * In Kotlin, operator overloading allows you to define or customize the behavior of standard operators
 * (like +, -, *, etc.) for your custom classes using specially named functions.
 * The function unaryMinus() in your example is one of those.
 */

data class Coordinates(val x: Int, val y: Int, val z: Int) {
    operator fun unaryMinus(): Coordinates {
        return Coordinates(-x, -y, -z)
    }

    operator fun unaryPlus() {
        println("nhi krene dungaa")
    }

    operator fun invoke() {
        println("i wont let you do anything")
    }

    operator fun plus(obj: Coordinates) {
        println("nhi krene dungaa")
    }

    operator fun times(obj: Coordinates) {
        println("We can modify multiply as well")
    }
}

fun main() {
    val obj = Coordinates(23, 78, 90)
    obj()
    obj + obj
    obj * obj
    -obj
    +obj
    obj * obj
}