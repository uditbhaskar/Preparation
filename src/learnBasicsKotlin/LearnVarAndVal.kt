package learnBasicsKotlin
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
    val str: String? = newObj as? String
    println(str)
}