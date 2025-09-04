package advanceKotlin.advanceClasses

/**
 * # Sealed Classes in Kotlin
 * 
 * This file demonstrates sealed classes and their usage including:
 * - Sealed class hierarchy with different vehicle types
 * - Pattern matching with when expressions
 * - Infix functions and operator overloading
 * - Value classes and type aliases
 * 
 * ## Topics Covered:
 * - **Sealed Class**: Restricted class hierarchies where all subclasses are known at compile time (Vehicle)
 * - **When Expression**: Exhaustive pattern matching with sealed classes (when(hybrid))
 * - **Infix Function**: Functions that can be called with infix notation (combineWith)
 * - **Value Class**: Wrapper around a single value with compile-time optimizations (Kilogram, PassengerCount)
 * - **Type Alias**: Alternative names for existing types (bike = Vehicle.Motorcycle)
 * 
 * ## Examples:
 * - **Vehicle**: Sealed class with Motorcycle, SUV, Tricycle subclasses
 * - **combineWith**: Infix function to combine two vehicles
 * - **Kilogram/PassengerCount**: Value classes for type safety
 * - **CustomVehicle**: Private sealed subclass for combinations
 * 
 * @author Udit
 * @since 1.0
 */

@JvmInline
value class Kilogram(private val kg: Int)

@JvmInline
value class PassengerCount(private val count: Int)

typealias bike = Vehicle.Motorcycle

fun Int.kg(): Kilogram = Kilogram(this)

sealed class Vehicle(val isTwoWheeler: Boolean, val mileage: Int) {
    class Motorcycle(val price: Int) : Vehicle(true, 55)
    class SportsUtilityVehicle(val price: Int, val weight: Kilogram) : Vehicle(false, 15)
    class Tricycle(val price: Int, val passengerCapacity: PassengerCount) : Vehicle(false, 60)

    private class CustomVehicle(isTwoWheeler: Boolean, mileage: Int) : Vehicle(isTwoWheeler, mileage) {
        override fun toString(): String = """
            Is Two Wheeler: $isTwoWheeler
            Mileage: $mileage km/l
        """.trimIndent()
    }

    infix fun combineWith(other: Vehicle): Vehicle = CustomVehicle(
        isTwoWheeler = this.isTwoWheeler || other.isTwoWheeler,
        mileage = maxOf(this.mileage, other.mileage)
    )
}

fun main() {
    val motorcycle = Vehicle.Motorcycle(price = 120_000)
    val suv = Vehicle.SportsUtilityVehicle(price = 2_500_000, weight = 1800.kg())
    val tricycle = Vehicle.Tricycle(price = 80_000, passengerCapacity = PassengerCount(3))

    println("Motorcycle: isTwoWheeler=${motorcycle.isTwoWheeler}, mileage=${motorcycle.mileage}")
    println("SUV: isTwoWheeler=${suv.isTwoWheeler}, mileage=${suv.mileage}")
    println("Tricycle: isTwoWheeler=${tricycle.isTwoWheeler}, mileage=${tricycle.mileage}")

    val hybrid = motorcycle combineWith suv
    println("\nCombined Vehicle (Motorcycle + SUV):\n$hybrid")

    when(hybrid){
        is Vehicle.Tricycle -> {}
        is bike -> {} // here used typealias
        is Vehicle.SportsUtilityVehicle -> {}
        else -> {}
        // Using else because CustomVehicle is private and when statement needs to be exhaustive
    }

    val superHybrid = hybrid combineWith tricycle
    println("\nCombined Vehicle (Hybrid + Tricycle):\n$superHybrid")

    val ultraHybrid = suv combineWith tricycle
    println("\nCombined Vehicle (SUV + Tricycle):\n$ultraHybrid")
}