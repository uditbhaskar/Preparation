package solidPrincipal

/**
 * # Dependency Inversion Principle (DIP)
 * 
 * This file demonstrates the Dependency Inversion Principle from SOLID principles.
 * Shows how to depend on abstractions rather than concrete implementations.
 * 
 * ## Topics Covered:
 * - **Dependency Inversion**: High-level modules should not depend on low-level modules
 * - **Abstraction**: Both should depend on abstractions (interfaces)
 * - **Interface Segregation**: Clients should not depend on interfaces they don't use
 * - **Dependency Injection**: Providing dependencies from outside
 * - **Loose Coupling**: Reducing dependencies between components
 * 
 * @author Udit
 * @since 1.0
 */

/*
// Bad version - violates DIP
class SQLDatabase(){
    fun saveUser() = println("User saved.")
}

class UserRepository {
    private val database = SQLDatabase() // Direct dependency on concrete class
    fun saveFetchedUser(db: SQLDatabase){
        db.saveUser()
    }
}
*/

interface DatabaseFeatures{
    fun saveUser()
}

class SQLDatabase : DatabaseFeatures {
    override fun saveUser() = println("User saved")
}

class RoomDatabase : DatabaseFeatures {
    override fun saveUser() = println("User saved")
}

class UserRepository(val db: DatabaseFeatures) {
    fun saveFetchedUser(){
        db.saveUser()
    }
}

fun main(){
    val db = SQLDatabase()
    val repository = UserRepository(db)
    repository.saveFetchedUser()

    val anotherDb = RoomDatabase()
    val repositoryAnother = UserRepository(anotherDb)
    repositoryAnother.saveFetchedUser()
}

