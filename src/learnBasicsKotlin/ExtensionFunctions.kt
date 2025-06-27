package learnBasicsKotlin

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