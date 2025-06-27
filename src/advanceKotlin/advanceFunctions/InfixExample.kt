package advanceKotlin.advanceFunctions

    class Miami {
        infix fun hasBesBeaches(isBest: Boolean) {
            println("The statement is $isBest that Miami has best beaches in the world.")
        }


    }

    fun main() {
        val obj = Miami()
        obj hasBesBeaches true
        obj isBeautiful true
        println(23 getAverage 4.0f)
        println(obj)

        val n = normal(23)
        println(n)
    }

    infix fun Miami.isBeautiful(isBeautiful: Boolean) =
        println("The statement is $isBeautiful that Miami is very beautiful.")


    infix fun Int.getAverage(totalSubjects: Float): Float {
        return this / totalSubjects
    }



data class normal(val c:Int)