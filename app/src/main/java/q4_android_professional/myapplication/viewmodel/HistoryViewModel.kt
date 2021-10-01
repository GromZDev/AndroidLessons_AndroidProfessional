package q4_android_professional.myapplication.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import myapplication.core.viewmodel.BaseViewModel
import myapplication.model.data.AppState
import myapplication.repository.parseLocalSearchResults
import q4_android_professional.myapplication.interactor.HistoryInterActor

class HistoryViewModel(
    private val interActor: HistoryInterActor
) : BaseViewModel<AppState>() {

    private val livedataToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return livedataToObserve
    }

    /** Coroutines -  */
    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch { startInterActor(word, isOnline) }
    }

    private suspend fun startInterActor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interActor.getData(word, isOnline)))
    }


    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}