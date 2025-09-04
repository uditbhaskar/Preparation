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
 * - **Primary Constructor**: The main constructor defined in the class header
 * - **Init Block**: Code that runs when an instance is created
 * - **Secondary Constructor**: Additional constructors using the `constructor` keyword
 * - **Constructor Chaining**: Calling one constructor from another using `this()`
 * - **Inheritance**: How constructors work with class inheritance
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

class Vehicle(val model: String, val price: Int) {

    var owner: String = "Unknown"

    init {
        println("Init: Vehicle $model worth ₹$price created")
    }

    /**
     * Secondary constructor that allows setting the owner.
     * 
     * @param model The vehicle model
     * @param price The vehicle price  
     * @param owner The vehicle owner
     */
    constructor(model: String, price: Int, owner: String) : this(model, price) {
        this.owner = owner
        println("Secondary constructor: Owner set to $owner")
    }

    /**
     * Returns a string representation of the Vehicle object.
     * 
     * @return A formatted string containing the vehicle's model, price, and owner
     */
    override fun toString(): String = 
        "Vehicle(model=$model, price=₹$price, owner=$owner)"
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

    println("\n---- Vehicle Constructors ----")
    val v1 = Vehicle("Tesla Model 3", 40_00_000)
    val v2 = Vehicle("Nissan GT-R", 95_00_000, "Udit")
    println("Vehicle 1 object: $v1")
    println("Vehicle 2 object: $v2")

    println("\n---- Rectangle Constructors ----")
    val square = Rectangle(5)
    val rectangle = Rectangle(4, 6)
    println("Square object: $square")
    println("Rectangle object: $rectangle")
}

