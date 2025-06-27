package advanceKotlin.advanceFunctions

val isEven = fun (n: Int): Boolean { return n % 2 == 0 }
val isEvenConvertedToLambda: (Int)->Boolean = { a->  a % 2 == 0}
val greet: (String) -> String = { name -> "Hello, $name" }
val greetConvertedToAnonymous = fun(name: String): String { return "Hello, $name" }


fun main() {
    println(isEven(23))
    println(greet("MOTI"))
}











