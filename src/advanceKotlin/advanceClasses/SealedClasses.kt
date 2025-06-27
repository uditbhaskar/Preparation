package advanceKotlin.advanceClasses

sealed class Vehicle(isTwoWheeler: Boolean, mileage: Int) {
    class Bike(price: Int): Vehicle(true, 50)
    class SUV(price:Int, weight: Kilogram): Vehicle(false, 18)
    class TriCycle(price: Int, numberOfPeople: Person): Vehicle(false, Int.MAX_VALUE)
}

@JvmInline
value class Kilogram(private val kg: Int)

@JvmInline
value class Person(private val person: Int)

fun main(){

}