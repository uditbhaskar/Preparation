package thread

/**
 * # Thread Basics in Kotlin/Java
 * 
 * This file demonstrates basic thread creation and execution.
 * Shows different ways to create and start threads.
 * 
 * ## Topics Covered:
 * - **Thread Creation**: Creating threads with lambda expressions
 * - **Thread.start()**: Starting thread execution
 * - **Thread.run()**: Direct execution without new thread
 * - **Concurrency**: Basic concepts of parallel execution
 * - **Background Tasks**: Running tasks in separate threads
 * 
 * @author Udit
 * @since 1.0
 */

fun main() {
    val exampleThread = Thread{
        println("Thread: ${Thread.currentThread().name}")
    }
    exampleThread.start()
    // Another way of same thing
    val task = {
        println("Running in background")
    }
    Thread(task).start()

    val exampleThreadB = Thread {
        println("Thread: ${Thread.currentThread().name}")
    }
    exampleThreadB.run()
}