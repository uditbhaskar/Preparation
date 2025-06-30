package advanceKotlin.advanceClasses

// Base class with primary constructor and init block
open class Persons(val name: String, val age: Int) {
    init {
        println("Init: Created Person(name=$name, age=$age)")
    }

    override fun toString(): String {
        return "Person(name=$name, age=$age)"
    }
}

// Employee class using primary constructor
class Employee(
    val jobTitle: String,
    val department: String,
    name: String,
    age: Int
) : Persons(name, age) {

    init {
        println("Init: Employee created with jobTitle=$jobTitle in $department department")
    }

    override fun toString(): String {
        return "Employee(name=$name, age=$age, jobTitle=$jobTitle, department=$department)"
    }
}

// Vehicle with primary + secondary constructor and object state logging
class Vehicles(val model: String, val price: Int) {

    var owner: String = "Unknown"

    init {
        println("Init: Vehicle $model worth ₹$price created")
    }

    constructor(model: String, price: Int, owner: String) : this(model, price) {
        this.owner = owner
        println("Secondary constructor: Owner set to $owner")
    }

    override fun toString(): String {
        return "Vehicle(model=$model, price=₹$price, owner=$owner)"
    }
}

// Rectangle with constructor chaining and area function
class Rectangle(val width: Int, val height: Int) {
    init {
        println("Init: Rectangle created with width=$width and height=$height")
    }

    constructor(side: Int) : this(side, side)

    fun area() = width * height

    override fun toString(): String {
        return "Rectangle(width=$width, height=$height, area=${area()})"
    }
}

// Main method to test all classes
fun main() {
    println("---- Person and Employee ----")
    val emp = Employee("Android Developer", "Engineering", "Alex", 26)
    println("Employee object: $emp")

    println("\n---- Vehicle Constructors ----")
    val v1 = Vehicles("Tesla Model 3", 40_00_000)
    val v2 = Vehicles("Nissan GT-R", 95_00_000, "Udit")
    println("Vehicle 1 object: $v1")
    println("Vehicle 2 object: $v2")

    println("\n---- Rectangle Constructors ----")
    val square = Rectangle(5)
    val rectangle = Rectangle(4, 6)
    println("Square object: $square")
    println("Rectangle object: $rectangle")
}
