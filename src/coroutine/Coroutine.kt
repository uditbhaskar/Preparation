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
