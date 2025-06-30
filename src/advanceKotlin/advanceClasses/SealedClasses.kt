package advanceKotlin.advanceClasses

sealed class Vehicle(val isTwoWheeler: Boolean, val mileage: Int) {
    class Motorcycle(val price: Int) : Vehicle(true, 55)
    class SportsUtilityVehicle(val price: Int, val weight: Kilogram) : Vehicle(false, 15)
    class Tricycle(val price: Int, val passengerCapacity: Person) : Vehicle(false, 60)

    private class CustomVehicle(isTwoWheeler: Boolean, mileage: Int) : Vehicle(isTwoWheeler, mileage) {
        override fun toString(): String {
            return """
                Is Two Wheeler: $isTwoWheeler
                Mileage: $mileage km/l
            """.trimIndent()
        }
    }

    infix fun combineWith(other: Vehicle): Vehicle {
        return CustomVehicle(
            isTwoWheeler = this.isTwoWheeler || other.isTwoWheeler,
            mileage = maxOf(this.mileage, other.mileage)
        )
    }
}

fun Int.kg(): Kilogram = Kilogram(this)
@JvmInline
value class Kilogram(private val kg: Int)

@JvmInline
value class Person(private val count: Int)

fun main() {
    val motorcycle = Vehicle.Motorcycle(price = 120_000)
    val suv = Vehicle.SportsUtilityVehicle(price = 2_500_000, weight = 1800.kg())
    val tricycle = Vehicle.Tricycle(price = 80_000, passengerCapacity = Person(3))

    println("Motorcycle: isTwoWheeler=${motorcycle.isTwoWheeler}, mileage=${motorcycle.mileage}")
    println("SUV: isTwoWheeler=${suv.isTwoWheeler}, mileage=${suv.mileage}")
    println("Tricycle: isTwoWheeler=${tricycle.isTwoWheeler}, mileage=${tricycle.mileage}")

    val hybrid = motorcycle combineWith suv
    println("\nCombined Vehicle (Motorcycle + SUV):\n$hybrid")

    val superHybrid = hybrid combineWith tricycle
    println("\nCombined Vehicle (Hybrid + Tricycle):\n$superHybrid")

    val ultraHybrid = suv combineWith tricycle
    println("\nCombined Vehicle (SUV + Tricycle):\n$ultraHybrid")

}