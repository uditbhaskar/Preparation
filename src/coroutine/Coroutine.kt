package coroutine

//put it inside a vieModel

/*suspend fun asyncCode(){
    withContext(Dispatchers.Unconfined + CoroutineName("Async Code")) {
        delay(2000)
    }
}

suspend fun anotherAsyncCode(){
    withContext(Dispatchers.Unconfined) {
        delay(2000)
    }
}

fun execute() {
    var job = SupervisorJob()
    var scope = CoroutineScope(job + Dispatchers.IO + CoroutineName("my name"))
    scope.launch(Dispatchers.Main) {
        try {
            withTimeout(2000) {
                val jobA = launch {
                    try {
                        asyncCode()
                    } catch (ex: CancellationException) {

                    }
                }
                val jobB = launch {
                    try {
                        anotherAsyncCode()
                    } catch (ex: CancellationException) {

                    }
                }

                print("done")
                if (job.isActive) job.cancel(CancellationException("Something went wrong"))
            }
        } catch (ex: TimeoutCancellationException) {

        }

        var jobC = launch(start = CoroutineStart.LAZY){

        }

        jobC.start()
    }
}*/

/*

suspend fun newCoroutine() {
    var handler = CoroutineExceptionHandler { context, throwable->
        print(throwable.message)
    }
    val job = CoroutineScope(context = Dispatchers.Main + CoroutineName("MY Coroutine") + handler)

    job.launch {

        val deferred1 = async { a1() }
        val deferred2 = async { a2() }
        val nestRes = withContext<Int>(CoroutineScope(Dispatchers.Default).coroutineContext) {
            a2()
        }
        processResult(deferred2.await(), deferred1.await(), nestRes)

    }

}

suspend fun a1():Int {
    return 33
}

suspend fun a2() :Int{
    return 33
}

suspend fun processResult(await: Int, await1: Int, nestRes: Int) {

}*/


/**
 *
 * 🔁 Scenario:
 * You launch multiple child coroutines (e.g., in SupervisorJob or viewModelScope) and don’t use try-catch inside each launch block.
 *
 * 🔥 What happens?
 * Each child coroutine runs independently (thanks to SupervisorJob).
 *
 * If a child throws an exception and it’s not caught inside the coroutine, then:
 *
 * For launch: it goes to the CoroutineExceptionHandler, if present.
 *
 * For async: the exception is stored and only thrown when .await() is called.
 *
 * Only the first uncaught exception in launch is delivered to the handler.
 *
 * Other failures are logged, but not re-thrown or re-handled.
 *
 * They're often silently ignored unless explicitly handled.
 *
 *
 * val handler = CoroutineExceptionHandler { _, exception ->
 *     println("🔥 Global Handler: ${exception.message}")
 * }
 *
 * val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main + handler)
 *
 * scope.launch {
 *     launch {
 *         delay(100)
 *         throw IllegalArgumentException("Job 1 failed")
 *     }
 *
 *     launch {
 *         delay(200)
 *         throw NullPointerException("Job 2 failed")
 *     }
 *
 *     launch {
 *         delay(300)
 *         throw IllegalStateException("Job 3 failed")
 *     }
 * }
 *
 */


/**
 *
 * 🚀 Summary: Dispatchers.IO vs Dispatchers.Default
 * Feature	Dispatchers.IO	Dispatchers.Default
 * Purpose	I/O-bound tasks (blocking ops)	CPU-bound tasks (intensive computation)
 * Thread pool size	Up to 64 threads (unbounded-like)	Limited: number of CPU cores
 * Backed by	Shared thread pool with relaxed limits	Shared ForkJoinPool or CPU core threads
 * Best for	File I/O, network, DB (blocking work)	Sorting, JSON parsing, image processing
 * Allows blocking?	✅ Yes — designed for blocking ops	❌ Avoid blocking — meant for pure CPU tasks
 *
 * 🧠 Internals:
 * 🔧 Dispatchers.Default:
 * Uses a limited thread pool (based on CPU cores, typically availableProcessors()).
 *
 * Ideal for CPU-bound operations where context switching should be minimal.
 *
 * Uses work-stealing algorithms via a ForkJoinPool-like system.
 *
 * If you run too many tasks here, it may get saturated, leading to performance degradation.
 *
 * 🔧 Dispatchers.IO:
 * Also runs on background threads, but...
 *
 * Uses a larger thread pool (up to 64 threads by default).
 *
 * Designed to handle blocking operations safely (e.g., Thread.sleep(), network, DB).
 *
 * If you block threads here, it just adds more threads, preventing starvation.
 *
 * ⚠️ Why not use IO for everything?
 * Dispatchers.IO has more lenient limits, but using it for compute-heavy tasks wastes resources.
 *
 * Default is more optimized for CPU cache, thread affinity, and minimizes overhead for CPU-bound coroutines.
 *
 * Blocking in Default can freeze the pool and make your app sluggish.
 *
 * 🔍 Real-world analogy:
 * Imagine:
 *
 * Dispatchers.Default = engineers doing heavy brain work → don't interrupt them, let them think.
 *
 * Dispatchers.IO = clerks doing file/network/database work → more of them can be spun up, but don’t ask them to do math.
 *
 * ✅ Rule of Thumb:
 * If you're doing...	Use...
 * Reading from disk or database	Dispatchers.IO
 * Making an API call	Dispatchers.IO
 * Parsing JSON, sorting large list	Dispatchers.Default
 * Image filtering or complex calculations	Dispatchers.Default
 * Writing to files or preferences	Dispatchers.IO
 */
