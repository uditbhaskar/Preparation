package solidPrincipal

/**
 * # Single Responsibility Principle (SRP)
 * 
 * This file demonstrates the Single Responsibility Principle from SOLID principles.
 * Shows how each class should have only one reason to change.
 * 
 * ## Topics Covered:
 * - **Single Responsibility**: Each class should have only one job
 * - **Separation of Concerns**: Dividing program into distinct sections
 * - **Class Design**: Designing cohesive, focused classes
 * - **Maintainability**: How SRP improves code maintenance
 * - **Code Organization**: Structuring code for clarity
 * 
 * @author Udit
 * @since 1.0
 */

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



