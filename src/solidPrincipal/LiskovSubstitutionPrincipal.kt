package solidPrincipal

/**
 *
 *
 */

/**
open class Student(val marks: Int? = null) {
    open fun marks(): Int? {
        return marks
    }
}

class Boy: Student (marks = 20){
    override fun marks():Int? {
        return super. Marks
    }
}

class Girl: Student (marks = 30){
    override fun marks():Int? {
        return super. Marks
    }
}

class Teacher: Student() {
    override fun marks (): Int {
        throw UnsupportedOperationException("Teachers don't get marks!")
    }
 }
*/

interface CanHaveMarks{
    fun getMarks(): Int
}

interface CurrentSalary{
    fun getSalary(): Int
}
class Boy: CanHaveMarks{
    override fun getMarks(): Int {
        return 23
    }

}
class Girl: CanHaveMarks{
    override fun getMarks(): Int {
        return 33
    }
}

class Teacher: CurrentSalary{
    override fun getSalary(): Int {
        return 30_000
    }
}





