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
 * ## 🔁 Scenario: Multiple child coroutines without try-catch
 *
 * You launch multiple child coroutines (e.g., in `SupervisorJob` or `viewModelScope`)
 * and don’t use `try-catch` inside each `launch` block.
 *
 * ---
 *
 * ### 🔥 What happens?
 * - Each child coroutine runs independently (due to `SupervisorJob`).
 * - If a child throws an exception and it’s not caught:
 *   - For `launch`: the exception goes to the `CoroutineExceptionHandler`, if defined.
 *   - For `async`: the exception is deferred and only thrown when `.await()` is called.
 * - Only the **first uncaught exception** from `launch` is delivered to the handler.
 * - Other failures are **logged** but **not re-thrown or re-handled**.
 * - They are often **silently ignored** unless explicitly handled.
 *
 * ---
 *
 * ### 🧪 Example:
 * ```kotlin
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
 * ```
 * ---
 *
 * ## 🚀 Summary: `Dispatchers.IO` vs `Dispatchers.Default`
 *
 * | Feature           | `Dispatchers.IO`                    | `Dispatchers.Default`                  |
 * |-------------------|-------------------------------------|----------------------------------------|
 * | **Purpose**        | I/O-bound tasks (blocking ops)      | CPU-bound tasks (intensive computation)|
 * | **Thread pool size** | Up to 64 threads (expandable)     | Limited (based on CPU cores)           |
 * | **Backed by**      | Relaxed shared thread pool          | ForkJoinPool or core-based pool        |
 * | **Best for**       | File I/O, DB, API calls             | Sorting, parsing, image processing     |
 * | **Blocking allowed?** | ✅ Yes — designed for blocking ops | ❌ No — use for pure CPU work only     |
 *
 * ---
 *
 * ### 🧠 Internals:
 *
 * #### 🔧 `Dispatchers.Default`:
 * - Uses limited thread pool (based on `availableProcessors()`).
 * - Ideal for CPU-bound work (minimize context switching).
 * - Uses work-stealing via ForkJoinPool-like mechanism.
 * - Can become saturated if overloaded, causing slowdown.
 *
 * #### 🔧 `Dispatchers.IO`:
 * - Also runs on background threads.
 * - Allows blocking (e.g., `Thread.sleep`, DB, network).
 * - Can grow up to 64 threads to avoid starvation.
 *
 * ---
 *
 * ### ⚠️ Why not use IO for everything?
 * - IO is lenient but inefficient for CPU-heavy tasks.
 * - Default is optimized for thread affinity, CPU cache, minimal overhead.
 * - Blocking in Default can freeze app performance.
 *
 * ---
 *
 * ### 🔍 Analogy:
 * - `Dispatchers.Default` = engineers doing deep thinking → avoid interruptions.
 * - `Dispatchers.IO` = clerks doing I/O work → scalable and safe to block.
 *
 * ---
 *
 * ### ✅ Rule of Thumb:
 *
 * | Task                                | Use                |
 * |-------------------------------------|---------------------|
 * | Reading from disk or DB             | `Dispatchers.IO`    |
 * | Making API/network call             | `Dispatchers.IO`    |
 * | Sorting, JSON parsing, heavy logic  | `Dispatchers.Default` |
 * | Image filtering, matrix operations  | `Dispatchers.Default` |
 * | Writing to files or SharedPrefs     | `Dispatchers.IO`    |
 */
fun main(){

}
