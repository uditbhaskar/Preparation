package thread

/**
 * In Kotlin, especially with coroutines, a Mutex is used to ensure mutual exclusion,
 * meaning only one coroutine can access a critical section at a time.
 * It's part of kotlinx.coroutines.sync.Mutex and works similarly to synchronized blocks in Java,
 * but it's non-blocking and coroutine-friendly.
 *
 * 🔐 What is Mutex?
 * Mutex (short for Mutual Exclusion) allows you to protect shared resources from concurrent access.
 * It is used in coroutine contexts where suspending is allowed.
 *
 * It’s like a lock — only one coroutine can "hold" the mutex at a time.
 *
 * If another coroutine tries to acquire it, it suspends until it's free.
 *
 * ✅ When to Use Mutex:
 *
 * You’re sharing mutable state between coroutines.
 *
 * You want to avoid race conditions.
 *
 * You want to replace synchronized blocks in a suspend-safe way.
 */

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
