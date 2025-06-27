package thread

/*
fun main(){
    val handler = Handler(Looper.getMainLooper()) // Access Main Thread
    handler.post {
        println("This runs on the Main/UI thread")
    }
}


fun runHeavyTask() {
    Thread {
        // Background work
        Thread.sleep(3000)

        // Update UI
        Handler(Looper.getMainLooper()).post {
            println("Update UI safely from background")
        }
    }.start()
}


*/


/**
 * 🧠 Problem with Threads + Handlers
 * Manually creating threads is error-prone.
 *
 * Thread leaks, no cancellation, lifecycle awareness is missing.
 *
 * Too much boilerplate to manage simple background tasks.
 *
 * No structured concurrency.
 */
