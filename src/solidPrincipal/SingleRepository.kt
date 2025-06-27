package solidPrincipal

/**
BAD: Violation of SRP
class UserManager {
    fun login() { // logic}
    fun saveUserToPrefs() { //writes to SharedPreferences }
}
*/

interface UserManager{
    fun saveUserName(name: String)
}


class UserManagerImpl: UserManager {
    override fun saveUserName(name: String) {
        /* writes to SharedPreferences */
    }
}

class LoginRepositoryImpl(): LoginRepository {
    override fun login() { /* logic */ }
}

interface LoginRepository {
    fun login()
}



