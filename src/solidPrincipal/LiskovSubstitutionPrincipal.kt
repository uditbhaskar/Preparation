package solidPrincipal

/**
 * # Liskov Substitution Principle (LSP)
 * 
 * This file demonstrates the Liskov Substitution Principle from SOLID principles.
 * Shows how subtypes must be substitutable for their base types.
 * 
 * ## Topics Covered:
 * - **Liskov Substitution**: Objects of superclass should be replaceable with objects of subclass
 * - **Behavioral Subtyping**: Subclasses should behave like their parent classes
 * - **Contract Preservation**: Maintaining the contract defined by the base class
 * - **Inheritance Design**: Proper inheritance hierarchies
 * - **Polymorphism**: Using inheritance for flexible code design
 * 
 * @author Udit
 * @since 1.0
 */

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





