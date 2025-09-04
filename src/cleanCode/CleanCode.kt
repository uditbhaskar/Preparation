package cleanCode

/**
 * # Clean Code Principles in Kotlin
 * 
 * This file demonstrates clean code principles and best practices.
 * Shows how to refactor code for better readability, maintainability, and testability.
 * 
 * ## Topics Covered:
 * - **Clean Code**: Writing code that is easy to read, understand, and maintain
 * - **Single Responsibility**: Each function/class should have one reason to change
 * - **Meaningful Names**: Using descriptive names for variables, functions, and classes
 * - **Code Organization**: Structuring code for better maintainability
 * - **Constants**: Extracting magic strings and numbers
 * - **Error Handling**: Proper exception handling patterns
 * 
 * Note: Android-specific code is commented out as it requires Android SDK.
 * 
 * @author Udit
 * @since 1.0
 */

/*
// Before refactoring - Android specific code
fun saveUserData(context: Context, name: String, age: Int, isPremium: Boolean) {
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    editor.putString("user_name", name)
    editor.putInt("user_age", age)
    editor.putBoolean("user_premium_status", isPremium)
    editor.commit()

    Focus on:
    1. Naming
    2. Single Responsibility
    3. Code readability
    4. Potential testability improvements
}
 */
/*



// Preference constants
object UserPreference {
    // Preference constants
    private const val USER_PREF = "user_prefs"
    private const val USER_NAME = "user_name"
    private const val USER_AGE = "user_age"
    private const val USER_PREMIUM_STATUS = "user_premium_status"

    */
/**
     * Saves user data in Shared Preference
     *
     * @param context required context
     * @param name name of the user
     * @param age age of the user
     * @param isPremium premium status of the user
     *//*

    fun saveUserData(context: Context, name: String, age: Int, isPremium: Boolean) {
        val sharedPref = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putString(USER_NAME, name)
            putInt(USER_AGE, age)
            putBoolean(USER_PREMIUM_STATUS, isPremium)
            apply()
        }
    }

    */
/**
     * Fetches Username from Shared Preference
     * @param context required context
     * @return Username or null if name not saved already
     *//*

    fun getUserName(context: Context): String? = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE).getString(USER_NAME, null)

    */
/**
     * Fetches User's age from Shared Preference
     * @param context required context
     * @return User age or -1 if User's age not saved already
     *//*

    fun getUserAge(context: Context): Int = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE).getInt(USER_AGE, -1)
    */
/**
     * Fetches User's premium status from Shared Preference
     * @param context required context
     * @return User premium status or false if User's premium status not saved already
     *//*

    fun getUserPremiumStatus(context: Context): Boolean = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE).getBoolean(
        USER_PREMIUM_STATUS, false)

        }
*/

fun main() {
    println("Clean Code examples are commented out - they require Android SDK")
    println("Key principles demonstrated:")
    println("- Extract constants for magic strings")
    println("- Use meaningful function and variable names")
    println("- Apply Single Responsibility Principle")
    println("- Organize code into logical modules")
    println("- Use proper error handling")
}
