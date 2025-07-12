package thread

import java.util.concurrent.atomic.AtomicBoolean

/**
 * ✅ What is AtomicBoolean?
 * AtomicBoolean is a class in java.util.concurrent.atomic that provides a thread-safe, lock-free way to read, write, and update a boolean value atomically.
 * ⚙️ How It Works Internally
 * AtomicBoolean uses low-level atomic CPU instructions (like CAS: Compare-And-Swap) via the Unsafe class in Java.
 *
 * 🔄 compareAndSet(expected, newValue) is the core method:
 * It checks if the current value is expected, and if yes, it sets it to newValue — all in a single atomic step.
 *
 * This prevents race conditions, even with many threads.
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



