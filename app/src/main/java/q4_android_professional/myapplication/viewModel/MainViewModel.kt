package q4_android_professional.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import q4_android_professional.myapplication.utils.*

class MainViewModel : ViewModel() {

    private val _mutableLiveData: MutableLiveData<String> = MutableLiveData()
    val liveData: LiveData<String> = _mutableLiveData

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
        CoroutineScope(
            Dispatchers.Default
                    + SupervisorJob()
        )
    )

    init {
        CoroutineScope(Dispatchers.Default + SupervisorJob()).launch {
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

}