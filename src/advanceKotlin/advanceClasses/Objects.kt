package advanceKotlin.advanceClasses

import java.util.Objects

fun main(){
    val c = object {
        val d = 90
        fun ninty(){
            println("Inside Object expression with value $d")
        }
    }
    c.ninty()
    val falcon = Falcon
    falcon.getIsOldAge() // first initialized here
    println(falcon.toString())
    Example.Factory.x   
}

data object Falcon{
    val age : Int = 20
    lateinit var isOld: String
    fun getIsOldAge(){
        isOld = "IS OLD"
    }

    override fun toString(): String {
        return """
            Age = $age
            IsOld = $isOld
        """.trimIndent()
    }

}

class Example{
    companion object Factory{
        val x = 20
    }
}