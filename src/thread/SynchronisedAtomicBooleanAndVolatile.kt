package thread

import java.util.concurrent.atomic.AtomicBoolean

/**
 * # Synchronized, AtomicBoolean, and Volatile in Kotlin
 * 
 * This file demonstrates thread-safe programming concepts in Kotlin.
 * Shows different approaches to handling concurrent access to shared data.
 * 
 * ## Topics Covered:
 * - **AtomicBoolean**: Thread-safe boolean operations using atomic instructions
 * - **Volatile**: Ensuring visibility of changes across threads
 * - **Compare-And-Swap (CAS)**: Lock-free atomic operations
 * - **Thread Safety**: Preventing race conditions in concurrent code
 * - **Memory Visibility**: How changes are propagated between threads
 * 
 * @author Udit
 * @since 1.0
 */

val initialized = AtomicBoolean(false)

fun initializeOnce() {
    if (initialized.compareAndSet(false, true)) {
        // This block runs only once
        println("Initialized!")
    } else {
        println("Already initialized")
    }
}

/**
 * 🔍 What is volatile?
 * Volatile is a keyword that ensures visibility of changes to a variable across threads.
 *
 * When a variable is declared volatile, any read or write to it goes directly to main memory. This prevents threads from caching its value in their local memory (CPU registers or thread-local cache).
 */

/**
 * ✅ Real-World Example: A Stoppable Thread
 * Let’s say you want to run a background task and stop it gracefully from another thread.
 *
 * ❌ Without @Volatile — doesn’t work reliably
 *
 * ------------------------------------------------------------------
 * var running = true
 *
 * fun main() {
 *     val worker = Thread {
 *         while (running) {
 *             println("Worker: checking for new data...")
 *             Thread.sleep(100)
 *         }
 *         println("Worker: stopped.")
 *     }
 *
 *     worker.start()
 *
 *     // Simulate user clicking "Stop" after 1 second
 *     Thread.sleep(1000)
 *     println("Main: sending stop signal")
 *     running = false
 * }
 * ------------------------------------------------------------------
 * ❗Problem
 * running = false might not be visible to the background thread due to CPU caching or JVM optimizations.
 *
 * The thread might loop forever, ignoring the update.
 *
 * ✅ With @Volatile — works correctly
 *
 *------------------------------------------------------------------
 * @Volatile
 * var running = true
 *
 * fun main() {
 *     val worker = Thread {
 *         while (running) {
 *             println("Worker: checking for new data...")
 *             Thread.sleep(100)
 *         }
 *         println("Worker: stopped.")
 *     }
 *
 *     worker.start()
 *
 *     // Simulate user clicking "Stop" after 1 second
 *     Thread.sleep(1000)
 *     println("Main: sending stop signal")
 *     running = false
 * }
 * ------------------------------------------------------------------
 * ✅ Output:
 * Working...
 * Working...
 * Working...
 * Working...
 * Working...
 * Stopped.
 * Now it works! ✅
 */

@Volatile
var running = true

fun main() {
    // Test AtomicBoolean
    println("Testing AtomicBoolean:")
    repeat(3) {
        Thread { initializeOnce() }.start()
    }
    
    Thread.sleep(100) // Wait for threads to complete
    
    // Test volatile
    println("\nTesting volatile:")
    val worker = Thread {
        while (running) {
            println("Worker: checking for new data...")
            Thread.sleep(100)
        }
        println("Worker: stopped.")
    }

    worker.start()

    // Simulate user clicking "Stop" after 1 second
    Thread.sleep(1000)
    println("Main: sending stop signal")
    running = false
}



