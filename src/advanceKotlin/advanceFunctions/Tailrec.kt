package advanceKotlin.advanceFunctions

//factorial

/**
 * if recursion has last operation as just recursive function and strictly not anything else it is qualified to be tail recursion
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