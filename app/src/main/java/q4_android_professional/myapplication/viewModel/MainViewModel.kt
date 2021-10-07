package q4_android_professional.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import q4_android_professional.myapplication.utils.*

class MainViewModel : ViewModel() {

    private val _mutableLiveData: MutableLiveData<String> = MutableLiveData()
    val liveData: LiveData<String> = _mutableLiveData

    private val viewModelCoroutineScope = CoroutineScope(
        SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        viewModelCoroutineScope
    )

    init {
        CoroutineScope(SupervisorJob()).launch {
            stopwatchListOrchestrator.ticker.collect {
                _mutableLiveData.postValue(it)
            }
        }
    }

    fun buttonStart() {
        stopwatchListOrchestrator.start()
    }

    fun buttonPause() {
        stopwatchListOrchestrator.pause()
    }

    fun buttonStop() {
        stopwatchListOrchestrator.stop()
    }

    override fun onCleared() {
        /** Coroutines - отменяем работу корутины */
        super.onCleared()
        cancelJob()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    private fun handleError(error: Throwable) {
        _mutableLiveData.postValue(error.toString())
    }

}