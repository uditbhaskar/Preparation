package valueClass



val Int.age
    get() = Age(this)

/*val Int.kg get() = Kilogram(this)*/

val Int.gram get() = Gram(this)

@JvmInline
value class Kilogram(private val kg: Int){
    override fun toString(): String {
        return "$kg Kilograms"
    }
}

@JvmInline
value class Gram(val gm: Int){
    override fun toString(): String {
        return "$gm Grams"
    }
}


@JvmInline
value class Age(private val age: Int){
    override fun toString(): String = "$age years old"
}
fun createStudent(age: Age): Student{
    return Student(age)
}

fun setWeight(weight: Kilogram): Teacher{
    return Teacher(weight)
}
data class Teacher(val weight: Kilogram)
data class Student(val age: Age)

fun main() {
    val res = Age(23)
    println(createStudent(res))
    println(setWeight(Kilogram(23) ))
    println(23.gram)
    val d = Distance(100)
    printDistance(d)
}

@JvmInline
value class Distance(val meters: Int)

fun printDistance(distance: Distance) {
    println("Distance: ${distance.meters} meters")
}
