package solidPrincipal

/**
 // Bad version
class SQLDatabase(){
    fun saveUser() = println("USer saved.")
}

class UserRepository {
    private val database = SQLDatabase()
    fun saveFetchedUser(db: SQLDatabase){
        db.saveUser()
    }
}*/

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

