package advanceKotlin.advanceClasses

/**
 * # Object Expressions and Declarations in Kotlin
 * 
 * This file demonstrates various object patterns in Kotlin including:
 * - Object expressions (anonymous objects)
 * - Object declarations (singletons)
 * - Companion objects
 * - Data objects
 * 
 * ## Topics Covered:
 * - **Object Expression**: Anonymous objects created on-the-fly
 * - **Object Declaration**: Singleton objects using the `object` keyword
 * - **Data Object**: Objects that can be used in data classes and when expressions
 * - **Companion Object**: Objects that belong to a class and can access private members
 * - **Lateinit**: Late initialization of properties
 * 
 * @author Udit
 * @since 1.0
 */

/**
 * Main function demonstrating object expressions and declarations.
 * 
 * Shows practical examples of:
 * - Creating anonymous objects with object expressions
 * - Using singleton objects with data objects
 * - Accessing companion object members
 * 
 * @author Udit
 */
fun main(){
    // Object Expression - Anonymous object created on-the-fly
    val anonymousObj = object {
        val value = MAGIC_NUMBER
        fun displayMessage(){
            println("$OBJECT_EXPRESSION_MESSAGE $value")
        }
    }
    anonymousObj.displayMessage()
    
    // Object Declaration - Singleton object
    val falcon = Falcon
    falcon.initializeAge() // Initialize lateinit property
    println(falcon.toString())
    
    // Companion Object - Accessing companion object member
    println("$FACTORY_VALUE_LABEL ${Example.Factory.factoryValue}")   
}

/**
 * Data object demonstrating singleton pattern with late initialization.
 * 
 * **Data Object**: A singleton object that can be used in data classes and when expressions.
 * Only one instance of this object will ever exist in the application.
 * 
 * **Lateinit**: The `isOld` property is declared as `lateinit var`, meaning it must be
 * initialized before use but can be initialized later than object creation.
 * 
 * **Singleton Pattern**: Accessing `Falcon` always returns the same instance.
 * 
 * @author Udit
 */
data object Falcon{
    val age: Int = DEFAULT_AGE
    lateinit var ageStatus: String
    
    /**
     * Initializes the lateinit property with age status.
     * 
     * This method must be called before accessing `ageStatus` to avoid
     * UninitializedPropertyAccessException.
     */
    fun initializeAge(){
        ageStatus = if (age >= SENIOR_AGE_THRESHOLD) SENIOR_STATUS else YOUNG_STATUS
    }

    /**
     * Returns a formatted string representation of the Falcon object.
     * 
     * @return A multi-line string containing age and status information
     */
    override fun toString(): String = """
        $AGE_LABEL $age $YEARS_SUFFIX
        $STATUS_LABEL $ageStatus
    """.trimIndent()
}

/**
 * Example class demonstrating companion objects.
 * 
 * **Companion Object**: A singleton object that belongs to the class and can access
 * private members. It's like static members in Java but more powerful.
 * 
 * @author Udit
 */
class Example{
    /**
     * Companion object with a custom name 'Factory'.
     * 
     * **Companion Object**: Can be accessed without creating an instance of the class.
     * Similar to static members in Java but with more features.
     */
    companion object Factory{
        val factoryValue = FACTORY_DEFAULT_VALUE
    }
}

// Constants for better maintainability
private const val MAGIC_NUMBER = 90
private const val DEFAULT_AGE = 20
private const val SENIOR_AGE_THRESHOLD = 18
private const val FACTORY_DEFAULT_VALUE = 20
private const val OBJECT_EXPRESSION_MESSAGE = "Inside Object expression with value"
private const val FACTORY_VALUE_LABEL = "Factory value:"
private const val SENIOR_STATUS = "SENIOR"
private const val YOUNG_STATUS = "YOUNG"
private const val AGE_LABEL = "Age ="
private const val STATUS_LABEL = "Status ="
private const val YEARS_SUFFIX = "years"