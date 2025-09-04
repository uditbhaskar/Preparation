package thread

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * # Mutex and Synchronization in Kotlin
 * 
 * This file demonstrates mutex and synchronization concepts for thread-safe operations.
 * Shows different approaches to prevent race conditions in concurrent code.
 * 
 * ## Topics Covered:
 * - **Mutex**: Mutual exclusion primitive for thread safety
 * - **ReentrantLock**: Java's reentrant mutual exclusion lock
 * - **Race Conditions**: Problems with concurrent access to shared state
 * - **Thread Safety**: Protecting shared resources from concurrent modification
 * - **Critical Sections**: Code that must be executed by only one thread at a time
 * - **Synchronization**: Coordinating access to shared resources
 * 
 * @author Udit
 * @since 1.0
 */

/**
 * Example showing race conditions without synchronization.
 */
class UnsafeCounter {
    private var count = 0
    
    fun increment() {
        count++ // Race condition: read-modify-write is not atomic
    }
    
    fun getCount(): Int = count
}

/**
 * Example using ReentrantLock for thread-safe operations.
 */
class MutexCounter {
    private var count = 0
    private val mutex = ReentrantLock()
    
    /**
     * Thread-safe increment using mutex lock.
     */
    fun increment() {
        mutex.withLock {
            count++
        }
    }
    
    /**
     * Thread-safe decrement using mutex lock.
     */
    fun decrement() {
        mutex.withLock {
            count--
        }
    }
    
    /**
     * Thread-safe getter using mutex lock.
     */
    fun getCount(): Int {
        return mutex.withLock {
            count
        }
    }
}

/**
 * Example showing different synchronization patterns.
 */
class BankAccount(private var balance: Double) {
    private val lock = ReentrantLock()
    
    /**
     * Thread-safe deposit operation.
     */
    fun deposit(amount: Double) {
        lock.withLock {
            println("${Thread.currentThread().name}: Depositing $amount")
            Thread.sleep(10) // Simulate processing time
            balance += amount
            println("${Thread.currentThread().name}: New balance after deposit: $balance")
        }
    }
    
    /**
     * Thread-safe withdrawal operation.
     */
    fun withdraw(amount: Double): Boolean {
        return lock.withLock {
            if (balance >= amount) {
                println("${Thread.currentThread().name}: Withdrawing $amount")
                Thread.sleep(10) // Simulate processing time
                balance -= amount
                println("${Thread.currentThread().name}: New balance after withdrawal: $balance")
                true
            } else {
                println("${Thread.currentThread().name}: Insufficient funds for withdrawal of $amount")
                false
            }
        }
    }
    
    /**
     * Thread-safe balance check.
     */
    fun getBalance(): Double {
        return lock.withLock {
            balance
        }
    }
}

/**
 * Example of producer-consumer pattern with synchronization.
 */
class Buffer<T>(private val capacity: Int) {
    private val buffer = mutableListOf<T>()
    private val lock = ReentrantLock()
    private val notFull = lock.newCondition()
    private val notEmpty = lock.newCondition()
    
    /**
     * Producer method - adds item to buffer.
     */
    fun produce(item: T) {
        lock.withLock {
            while (buffer.size >= capacity) {
                println("${Thread.currentThread().name}: Buffer full, waiting...")
                notFull.await()
            }
            buffer.add(item)
            println("${Thread.currentThread().name}: Produced $item, buffer size: ${buffer.size}")
            notEmpty.signal()
        }
    }
    
    /**
     * Consumer method - removes item from buffer.
     */
    fun consume(): T? {
        return lock.withLock {
            while (buffer.isEmpty()) {
                println("${Thread.currentThread().name}: Buffer empty, waiting...")
                notEmpty.await()
            }
            val item = buffer.removeAt(0)
            println("${Thread.currentThread().name}: Consumed $item, buffer size: ${buffer.size}")
            notFull.signal()
            item
        }
    }
}

/**
 * Singleton pattern with thread-safe lazy initialization.
 */
class ThreadSafeSingleton private constructor() {
    companion object {
        @Volatile
        private var INSTANCE: ThreadSafeSingleton? = null
        private val lock = ReentrantLock()
        
        /**
         * Thread-safe singleton instance getter using double-checked locking.
         */
        fun getInstance(): ThreadSafeSingleton {
            if (INSTANCE == null) {
                lock.withLock {
                    if (INSTANCE == null) {
                        INSTANCE = ThreadSafeSingleton()
                        println("${Thread.currentThread().name}: Singleton instance created")
                    }
                }
            }
            return INSTANCE!!
        }
    }
    
    fun doSomething() {
        println("${Thread.currentThread().name}: Singleton doing work")
    }
}

/**
 * Demonstrates race condition and mutex solutions.
 */
fun main() {
    println("=== RACE CONDITION DEMO ===")
    demonstrateRaceCondition()
    
    println("\n=== MUTEX COUNTER DEMO ===")
    demonstrateMutexCounter()
    
    println("\n=== BANK ACCOUNT DEMO ===")
    demonstrateBankAccount()
    
    println("\n=== PRODUCER-CONSUMER DEMO ===")
    demonstrateProducerConsumer()
    
    println("\n=== THREAD-SAFE SINGLETON DEMO ===")
    demonstrateSingleton()
}

/**
 * Shows race condition with unsafe counter.
 */
fun demonstrateRaceCondition() {
    val unsafeCounter = UnsafeCounter()
    val threads = (1..5).map {
        Thread {
            repeat(1000) { unsafeCounter.increment() }
        }
    }
    
    threads.forEach { it.start() }
    threads.forEach { it.join() }
    
    println("Expected: 5000, Actual: ${unsafeCounter.getCount()}")
    println("Race condition likely caused count to be less than expected")
}

/**
 * Shows thread-safe operations with mutex.
 */
fun demonstrateMutexCounter() {
    val mutexCounter = MutexCounter()
    val threads = (1..5).map {
        Thread {
            repeat(1000) { mutexCounter.increment() }
        }
    }
    
    threads.forEach { it.start() }
    threads.forEach { it.join() }
    
    println("Expected: 5000, Actual: ${mutexCounter.getCount()}")
    println("Mutex ensures correct count")
}

/**
 * Shows synchronized bank operations.
 */
fun demonstrateBankAccount() {
    val account = BankAccount(1000.0)
    
    val threads = listOf(
        Thread { account.deposit(100.0) },
        Thread { account.withdraw(50.0) },
        Thread { account.deposit(200.0) },
        Thread { account.withdraw(75.0) }
    )
    
    threads.forEach { it.start() }
    threads.forEach { it.join() }
    
    println("Final balance: ${account.getBalance()}")
}

/**
 * Shows producer-consumer pattern with buffer.
 */
fun demonstrateProducerConsumer() {
    val buffer = Buffer<String>(3)
    
    val producer = Thread {
        repeat(5) {
            buffer.produce("Item-$it")
            Thread.sleep(100)
        }
    }
    
    val consumer = Thread {
        repeat(5) {
            buffer.consume()
            Thread.sleep(150)
        }
    }
    
    producer.start()
    consumer.start()
    
    producer.join()
    consumer.join()
}

/**
 * Shows thread-safe singleton creation.
 */
fun demonstrateSingleton() {
    val threads = (1..5).map {
        Thread {
            val instance = ThreadSafeSingleton.getInstance()
            instance.doSomething()
        }
    }
    
    threads.forEach { it.start() }
    threads.forEach { it.join() }
}

/*
=== KOTLIN COROUTINES MUTEX EXAMPLES (Commented - requires kotlinx.coroutines) ===

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Coroutine-based mutex example without synchronization.
 */
suspend fun noMutexExample() = coroutineScope {
    var count = 0

    val jobs = (1..10).map {
        launch(Dispatchers.Default) {
            repeat(1000) {
                count++ // Race condition
            }
        }
    }

    jobs.joinAll()
    println("Without mutex - Expected: 10000, Actual: $count")
}

/**
 * Coroutine-based mutex example with proper synchronization.
 */
suspend fun withMutexExample() = coroutineScope {
    var count = 0
    val mutex = Mutex()

    val jobs = (1..10).map {
        launch(Dispatchers.Default) {
            repeat(1000) {
                mutex.withLock {
                    count++
                }
            }
        }
    }

    jobs.joinAll()
    println("With mutex - Expected: 10000, Actual: $count")
}

/**
 * Android-specific example with coroutines and mutex.
 */
class AndroidDataManager {
    private var cache: MutableMap<String, String> = mutableMapOf()
    private val mutex = Mutex()
    
    suspend fun updateCache(key: String, value: String) {
        mutex.withLock {
            cache[key] = value
            // Simulate database write
            delay(10)
        }
    }
    
    suspend fun getFromCache(key: String): String? {
        return mutex.withLock {
            cache[key]
        }
    }
}
*/