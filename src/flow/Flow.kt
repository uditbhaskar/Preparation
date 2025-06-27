package flow

import kotlin.math.max

/**
suspend fun testFlow(){
    var flow = flow<Int> {
        emit(33)
        emitAll(listOf(1,2,3,4).asFlow())
        for(i in 1..6) emit(i)
    }

    flow.stateIn(viewModelScope)
    var scope = CoroutineScope(Dispatchers.Main)
    var anotherFlow = listOf("","sdw").asFlow()
    var x = flowOf(2,3).launchIn(scope)

    var hotFlowShared = MutableSharedFlow<Int>()
    val _hotFlowShared: SharedFlow<Int> = hotFlowShared
    hotFlowShared.map { it * it }
        .filter { it <=4 }
        .buffer(10)
        .collect {

        }
    var t = _hotFlowShared
    val hotFlowState = MutableStateFlow<Int>(23)

    hotFlowShared.emit(23)


    viewModelScope.launch {
        flow.collect { value->
            print(value)
        }
        //both will get the elements from beginning

        flow.collect { value->
            print(value)
        }

        hotFlowShared.collect {
            print(it)
        }

        hotFlowState.collect {
            print(it)
        }
        flow{
            try {
                emit(1)
                emit(2)
                throw Exception("Bad emit")
            } catch (e: Exception){
                print("Caught Exception ${e.message}")
            }

        }.retry(3){
            print("Caught Exception: ${it.message}")
            it is Exception
        }.onCompletion { cause->
            if(cause!= null){
                print("Flow with error: ${cause.message.toString()}")
            } else {
                print("Success")
            }
        }.catch { e->
            print("Exception Caught${e.message.toString()}")
        }.collect {
            print(it)
        }

    }
}*/


/**
 * import androidx.lifecycle.ViewModel
 * import androidx.lifecycle.viewModelScope
 * import androidx.lifecycle.viewmodel.compose.viewModel
 * import kotlinx.coroutines.ExperimentalCoroutinesApi
 * import kotlinx.coroutines.FlowPreview
 * import kotlinx.coroutines.delay
 * import kotlinx.coroutines.flow.Flow
 * import kotlinx.coroutines.flow.MutableStateFlow
 * import kotlinx.coroutines.flow.StateFlow
 * import kotlinx.coroutines.flow.debounce
 * import kotlinx.coroutines.flow.distinctUntilChanged
 * import kotlinx.coroutines.flow.flatMapLatest
 * import kotlinx.coroutines.flow.flow
 * import kotlinx.coroutines.launch
 *
 * class MainShowcaseViewModel : ViewModel() {
 *     var firstVisibleItemIndex: Int = 0
 *     var firstVisibleItemScrollOffset: Int = 0
 *     private val _searchQuery = MutableStateFlow("")
 *     val searchQuery: StateFlow<String> = _searchQuery
 *     private val _searchResults = MutableStateFlow<List<String>>(emptyList())
 *     val searchResults: StateFlow<List<String>> = _searchResults
 *
 *     init {
 *         observeSearchQuery()
 *     }
 *
 *     fun onQueryChanged(query: String) {
 *         _searchQuery.value = query
 *     }
 *
 *     @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
 *     private fun observeSearchQuery() {
 *         viewModelScope.launch {
 *             _searchQuery
 *                 .debounce(300) // wait for user to stop typing
 *                 .distinctUntilChanged()
 *                 .flatMapLatest { query ->
 *                     searchApi(query)
 *                 }
 *                 .collect { results ->
 *                     _searchResults.value = results
 *                 }
 *         }
 *     }
 *
 *     // Simulated fake API that returns a flow
 *     private fun searchApi(query: String): Flow<List<String>> = flow {
 *         if (query.isBlank()) {
 *             emit(emptyList())
 *         } else {
 *             delay(500) // simulate network delay
 *             emit(
 *                 listOf("Result for \"$query\" 1", "Result for \"$query\" 2")
 *             )
 *         }
 *     }
 * }
 */