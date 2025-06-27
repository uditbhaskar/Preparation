package thread

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