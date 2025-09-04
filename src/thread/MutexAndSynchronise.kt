package thread

/**
 * # Mutex and Synchronization in Kotlin Coroutines
 * 
 * This file demonstrates mutex usage for thread-safe operations in coroutines.
 * Shows how to prevent race conditions in concurrent code.
 * 
 * ## Topics Covered:
 * - **Mutex**: Mutual exclusion primitive for coroutines
 * - **Race Conditions**: Problems with concurrent access to shared state
 * - **Thread Safety**: Protecting shared resources from concurrent modification
 * - **Coroutine Synchronization**: Non-blocking synchronization primitives
 * - **Critical Sections**: Code that must be executed by only one thread at a time
 * 
 * Note: This file contains commented Android/Coroutine specific code that would need
 * kotlinx.coroutines dependency to run.
 * 
 * @author Udit
 * @since 1.0
 */
fun main() {
    println("Mutex examples are commented out - they require kotlinx.coroutines dependency")
}

/**
fun main() {
    noMutexExample()
    withMutexExample()
}

fun noMutexExample() = runBlocking {
    var count = 0

    val job1 = launch(Dispatchers.Default) {
        repeat(1_000_000) {
            count++
        }
    }

    val job2 = launch(Dispatchers.Default) {
        repeat(1_000_000) {
            count++
        }
    }

    joinAll(job1, job2)
    println("Final counter = $count") // Should be 2_000_000, but will likely be much lower
}

fun withMutexExample() = runBlocking {
    var count = 0
    val mutex = Mutex()

    val job1 = launch(Dispatchers.Default) {
        repeat(1_000_000) {
            mutex.withLock {
                count++
            }
        }
    }

    val job2 = launch(Dispatchers.Default) {
        repeat(1_000_000) {
            mutex.withLock {
                count++
            }
        }
    }

    joinAll(job1, job2)
    println("Final counter = $count") // Should be exactly 2_000_000
}*/
