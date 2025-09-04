package advanceKotlin.advanceClasses

/**
 * # Constructor Examples in Kotlin
 * 
 * This file demonstrates various constructor patterns in Kotlin including:
 * - Primary constructors with parameters
 * - Init blocks for initialization logic
 * - Secondary constructors with constructor chaining
 * - Inheritance with constructors
 * 
 * ## Topics Covered:
 * - **Primary Constructor**: The main constructor defined in the class header (Person, Car, Rectangle)
 * - **Init Block**: Code that runs when an instance is created (all classes show this)
 * - **Secondary Constructor**: Additional constructors using the `constructor` keyword (Car, Rectangle)
 * - **Constructor Chaining**: Calling one constructor from another using `this()` (Car, Rectangle)
 * - **Inheritance**: How constructors work with class inheritance (Employee extends Person)
 * 
 * ## Examples:
 * - **Person**: Basic primary constructor with init block
 * - **Employee**: Inheritance - passes parameters to parent constructor
 * - **Car**: Primary + secondary constructor with property initialization
 * - **Rectangle**: Constructor chaining to create squares from rectangles
 * 
 * @author Udit
 * @since 1.0
 */

open class Person(val name: String, val age: Int) {
    init {
        println("Init: Created Person(name=$name, age=$age)")
    }

    /**
     * Returns a string representation of the Person object.
     * 
     * @return A formatted string containing the person's name and age
     */
    override fun toString(): String = "Person(name=$name, age=$age)"
}

class Employee(
    val jobTitle: String,
    val department: String,
    name: String,
    age: Int
) : Person(name, age) {

    init {
        println("Init: Employee created with jobTitle=$jobTitle in $department department")
    }

    /**
     * Returns a string representation of the Employee object.
     * 
     * @return A formatted string containing the employee's name, age, job title, and department
     */
    override fun toString(): String = 
        "Employee(name=$name, age=$age, jobTitle=$jobTitle, department=$department)"
}

class Car(val model: String, val price: Int) {

    var owner: String = "Unknown"

    init {
        println("Init: Car $model worth ₹$price created")
    }

    /**
     * Secondary constructor that allows setting the owner.
     * 
     * @param model The car model
     * @param price The car price  
     * @param owner The car owner
     */
    constructor(model: String, price: Int, owner: String) : this(model, price) {
        this.owner = owner
        println("Secondary constructor: Owner set to $owner")
    }

    /**
     * Returns a string representation of the Car object.
     * 
     * @return A formatted string containing the car's model, price, and owner
     */
    override fun toString(): String = 
        "Car(model=$model, price=₹$price, owner=$owner)"
}

class Rectangle(val width: Int, val height: Int) {
    init {
        println("Init: Rectangle created with width=$width and height=$height")
    }

    /**
     * Secondary constructor for creating squares.
     * 
     * @param side The length of each side (creates a square)
     */
    constructor(side: Int) : this(side, side)

    /**
     * Calculates the area of the rectangle.
     * 
     * @return The area (width × height)
     */
    fun area() = width * height

    /**
     * Returns a string representation of the Rectangle object.
     * 
     * @return A formatted string containing the rectangle's width, height, and calculated area
     */
    override fun toString(): String = 
        "Rectangle(width=$width, height=$height, area=${area()})"
}

fun main() {
    println("---- Person and Employee ----")
    val emp = Employee("Android Developer", "Engineering", "Alex", 26)
    println("Employee object: $emp")

    println("\n---- Car Constructors ----")
    val v1 = Car("Tesla Model 3", 40_00_000)
    val v2 = Car("Nissan GT-R", 95_00_000, "Udit")
    println("Car 1 object: $v1")
    println("Car 2 object: $v2")

    println("\n---- Rectangle Constructors ----")
    val square = Rectangle(5)
    val rectangle = Rectangle(4, 6)
    println("Square object: $square")
    println("Rectangle object: $rectangle")
}

