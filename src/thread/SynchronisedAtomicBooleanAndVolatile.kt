package thread

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * # Synchronized, AtomicBoolean, and Volatile in Kotlin
 * 
 * This file demonstrates thread-safe programming concepts in Kotlin.
 * Shows different approaches to handling concurrent access to shared data.
 * 
 * ## Topics Covered:
 * - **Synchronized**: Using locks to ensure thread-safe access to shared resources
 * - **AtomicBoolean**: Thread-safe boolean operations using atomic instructions
 * - **Volatile**: Ensuring visibility of changes across threads
 * - **Compare-And-Swap (CAS)**: Lock-free atomic operations
 * - **Thread Safety**: Preventing race conditions in concurrent code
 * - **Memory Visibility**: How changes are propagated between threads
 * 
 * @author Udit
 * @since 1.0
 */

/**
 * Example class demonstrating synchronized methods and blocks.
 * Shows how to use locks for thread-safe access to shared resources.
 */
class SynchronizedCounter {
    private var count = 0
    
    /**
     * Synchronized method - only one thread can execute at a time.
     */
    @Synchronized
    fun increment() {
        count++
    }
    
    /**
     * Synchronized block - more granular control over locking.
     */
    fun decrement() {
        synchronized(this) {
            count--
        }
    }
    
    /**
     * Gets the current count in a thread-safe manner.
     */
    @Synchronized
    fun getCount(): Int = count
}

val initialized = AtomicBoolean(false)

/**
 * Demonstrates AtomicBoolean compareAndSet for single initialization.
 * Uses atomic compare-and-swap to ensure initialization runs only once.
 */
fun initializeOnce() {
    if (initialized.compareAndSet(false, true)) {
        println("Initialized! (This runs only once)")
        Thread.sleep(50) // Simulate initialization work
    } else {
        println("Already initialized")
    }
}

/**
 * Example class demonstrating AtomicBoolean for processing flags.
 * Shows lock-free thread-safe boolean operations.
 */
class AtomicFlagExample {
    private val isProcessing = AtomicBoolean(false)
    
    /**
     * Processes data only if not already processing.
     * Uses atomic flag to prevent concurrent processing.
     */
    fun processData() {
        if (isProcessing.compareAndSet(false, true)) {
            try {
                println("Processing data... Thread: ${Thread.currentThread().name}")
                Thread.sleep(100) // Simulate work
            } finally {
                isProcessing.set(false)
            }
        } else {
            println("Processing already in progress, skipping... Thread: ${Thread.currentThread().name}")
        }
    }
}

/**
 * Example showing potential visibility issues without volatile.
 */
class NonVolatileExample {
    private var flag = false
    
    fun writer() {
        Thread.sleep(100)
        flag = true
        println("Flag set to true")
    }
    
    fun reader() {
        while (!flag) {
            // This might loop forever due to CPU caching
        }
        println("Flag detected as true")
    }
}

/**
 * Example showing proper visibility with volatile.
 */
class VolatileExample {
    @Volatile
    private var flag = false
    
    fun writer() {
        Thread.sleep(100)
        flag = true
        println("Volatile flag set to true")
    }
    
    fun reader() {
        while (!flag) {
            Thread.sleep(1) // Small sleep to avoid busy waiting
        }
        println("Volatile flag detected as true")
    }
}

@Volatile
var isRunning = true

/**
 * Background worker that can be gracefully shutdown using volatile flag.
 */
fun backgroundWorker(workerId: Int) {
    while (isRunning) {
        println("Worker $workerId: processing...")
        Thread.sleep(200)
    }
    println("Worker $workerId: stopped gracefully")
}

/**
 * Demonstrates race conditions and compares different thread-safety approaches.
 */
class RaceConditionDemo {
    private var unsafeCounter = 0
    private var synchronizedCounter = 0
    private val atomicCounter = AtomicInteger(0)
    
    fun incrementUnsafe() {
        unsafeCounter++ // Race condition: read-modify-write is not atomic
    }
    
    @Synchronized
    fun incrementSynchronized() {
        synchronizedCounter++
    }
    
    fun incrementAtomic() {
        atomicCounter.incrementAndGet()
    }
    
    fun getResults(): String {
        return """
            Unsafe Counter: $unsafeCounter
            Synchronized Counter: $synchronizedCounter  
            Atomic Counter: ${atomicCounter.get()}
        """.trimIndent()
    }
}


fun main() {
    println("=== SYNCHRONIZED EXAMPLE ===")
    val syncCounter = SynchronizedCounter()
    val threads1 = (1..5).map {
        Thread {
            repeat(100) { syncCounter.increment() }
        }
    }
    threads1.forEach { it.start() }
    threads1.forEach { it.join() }
    println("Synchronized counter final value: ${syncCounter.getCount()}")
    
    println("\n=== ATOMIC BOOLEAN EXAMPLE ===")
    // Test AtomicBoolean - only first thread should initialize
    repeat(3) {
        Thread { initializeOnce() }.start()
    }
    Thread.sleep(200) // Wait for threads
    
    // Test atomic flag
    val atomicFlag = AtomicFlagExample()
    repeat(3) {
        Thread { atomicFlag.processData() }.start()
    }
    Thread.sleep(300)
    
    println("\n=== VOLATILE EXAMPLE ===")
    val volatileExample = VolatileExample()
    
    // Start reader thread
    val readerThread = Thread { volatileExample.reader() }
    readerThread.start()
    
    // Start writer thread
    Thread { volatileExample.writer() }.start()
    
    readerThread.join()
    
    println("\n=== GRACEFUL SHUTDOWN EXAMPLE ===")
    // Start multiple background workers
    val workers = (1..3).map { workerId ->
        Thread { backgroundWorker(workerId) }
    }
    workers.forEach { it.start() }
    
    // Let them run for a while
    Thread.sleep(1000)
    
    // Signal shutdown
    println("Signaling shutdown...")
    isRunning = false
    
    // Wait for all workers to stop
    workers.forEach { it.join() }
    println("All workers stopped")
    
    println("\n=== RACE CONDITION COMPARISON ===")
    val raceDemo = RaceConditionDemo()
    val numThreads = 10
    val incrementsPerThread = 1000
    
    // Test all three approaches
    val allThreads = mutableListOf<Thread>()
    
    repeat(numThreads) {
        allThreads.add(Thread {
            repeat(incrementsPerThread) {
                raceDemo.incrementUnsafe()
                raceDemo.incrementSynchronized()
                raceDemo.incrementAtomic()
            }
        })
    }
    
    allThreads.forEach { it.start() }
    allThreads.forEach { it.join() }
    
    println("Expected value for each counter: ${numThreads * incrementsPerThread}")
    println(raceDemo.getResults())
    println("\nNote: Unsafe counter likely shows race condition effects")
}

/*
=== ANDROID-SPECIFIC EXAMPLES (Commented) ===

// Using synchronized with Android SharedPreferences
class AndroidSyncExample(private val context: Context) {
    private val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val lock = Any()
    
    fun saveUserData(userId: String, data: String) {
        synchronized(lock) {
            sharedPrefs.edit()
                .putString("user_$userId", data)
                .apply()
        }
    }
}

// Using AtomicBoolean for network request flags
class NetworkManager {
    private val isRequestInProgress = AtomicBoolean(false)
    
    suspend fun fetchData(): String? {
        if (!isRequestInProgress.compareAndSet(false, true)) {
            Log.d("NetworkManager", "Request already in progress")
            return null
        }
        
        try {
            // Simulate network call
            delay(1000)
            return "Data fetched"
        } finally {
            isRequestInProgress.set(false)
        }
    }
}

// Using volatile for Activity lifecycle flags
class MainActivity : AppCompatActivity() {
    @Volatile
    private var isActivityDestroyed = false
    
    override fun onDestroy() {
        super.onDestroy()
        isActivityDestroyed = true
    }
    
    private fun updateUI() {
        if (!isActivityDestroyed) {
            runOnUiThread {
                // Safe to update UI
            }
        }
    }
}
*/