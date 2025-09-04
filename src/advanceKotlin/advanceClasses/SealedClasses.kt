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
 * - **Sealed Class**: Restricted class hierarchies where all subclasses are known at compile time
 * - **When Expression**: Exhaustive pattern matching with sealed classes
 * - **Infix Function**: Functions that can be called with infix notation
 * - **Value Class**: Wrapper around a single value with compile-time optimizations
 * - **Type Alias**: Alternative names for existing types
 * 
 * @author Udit
 * @since 1.0
 */

sealed class Vehicle(val isTwoWheeler: Boolean, val mileage: Int) {
    class Motorcycle(val price: Int) : Vehicle(true, 55)
    class SportsUtilityVehicle(val price: Int, val weight: Kilogram) : Vehicle(false, 15)
    class Tricycle(val price: Int, val passengerCapacity: Person) : Vehicle(false, 60)

    private class CustomVehicle(isTwoWheeler: Boolean, mileage: Int) : Vehicle(isTwoWheeler, mileage) {
        /**
         * Returns a formatted string representation of the custom vehicle.
         * 
         * @return Multi-line string with vehicle specifications
         */
        override fun toString(): String = """
            $TWO_WHEELER_LABEL $isTwoWheeler
            $MILEAGE_LABEL $mileage $MILEAGE_UNIT
        """.trimIndent()
    }

    /**
     * Combines two vehicles using infix notation to create a hybrid vehicle.
     * 
     * @param other The vehicle to combine with
     * @return A new CustomVehicle with combined properties
     */
    infix fun combineWith(other: Vehicle): Vehicle = CustomVehicle(
        isTwoWheeler = this.isTwoWheeler || other.isTwoWheeler,
        mileage = maxOf(this.mileage, other.mileage)
    )
}

typealias bike = Vehicle.Motorcycle

fun Int.kg(): Kilogram = Kilogram(this)
@JvmInline
value class Kilogram(private val kg: Int)

@JvmInline
value class Person(private val count: Int)

/**
 * Main function demonstrating sealed classes and pattern matching.
 * 
 * Shows practical examples of:
 * - Creating instances of sealed class subclasses
 * - Using infix functions
 * - Pattern matching with when expressions
 * - Type aliases in action
 * 
 * @author Udit
 */
fun main() {
    val motorcycle = Vehicle.Motorcycle(price = MOTORCYCLE_PRICE)
    val suv = Vehicle.SportsUtilityVehicle(price = SUV_PRICE, weight = SUV_WEIGHT.kg())
    val tricycle = Vehicle.Tricycle(price = TRICYCLE_PRICE, passengerCapacity = Person(TRICYCLE_CAPACITY))

    println("$MOTORCYCLE_LABEL isTwoWheeler=${motorcycle.isTwoWheeler}, mileage=${motorcycle.mileage}")
    println("$SUV_LABEL isTwoWheeler=${suv.isTwoWheeler}, mileage=${suv.mileage}")
    println("$TRICYCLE_LABEL isTwoWheeler=${tricycle.isTwoWheeler}, mileage=${tricycle.mileage}")

    val hybrid = motorcycle combineWith suv
    println("$NEWLINE$COMBINED_MOTORCYCLE_SUV_LABEL$NEWLINE$hybrid")

    when(hybrid){
        is Vehicle.Tricycle -> {}
        is bike -> {} // here used typealias
        is Vehicle.SportsUtilityVehicle -> {}
        else -> {}
        // Using else because CustomVehicle is private and when statement needs to be exhaustive
    }

    val superHybrid = hybrid combineWith tricycle
    println("$NEWLINE$COMBINED_HYBRID_TRICYCLE_LABEL$NEWLINE$superHybrid")

    val ultraHybrid = suv combineWith tricycle
    println("$NEWLINE$COMBINED_SUV_TRICYCLE_LABEL$NEWLINE$ultraHybrid")
}

// Constants for better maintainability
private const val MOTORCYCLE_PRICE = 120_000
private const val SUV_PRICE = 2_500_000
private const val SUV_WEIGHT = 1800
private const val TRICYCLE_PRICE = 80_000
private const val TRICYCLE_CAPACITY = 3
private const val NEWLINE = "\n"
private const val TWO_WHEELER_LABEL = "Is Two Wheeler:"
private const val MILEAGE_LABEL = "Mileage:"
private const val MILEAGE_UNIT = "km/l"
private const val MOTORCYCLE_LABEL = "Motorcycle:"
private const val SUV_LABEL = "SUV:"
private const val TRICYCLE_LABEL = "Tricycle:"
private const val COMBINED_MOTORCYCLE_SUV_LABEL = "Combined Vehicle (Motorcycle + SUV):"
private const val COMBINED_HYBRID_TRICYCLE_LABEL = "Combined Vehicle (Hybrid + Tricycle):"
private const val COMBINED_SUV_TRICYCLE_LABEL = "Combined Vehicle (SUV + Tricycle):"