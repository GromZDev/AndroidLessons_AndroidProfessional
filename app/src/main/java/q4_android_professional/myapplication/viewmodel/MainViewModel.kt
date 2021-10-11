package q4_android_professional.myapplication.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import myapplication.core.viewmodel.BaseViewModel
import myapplication.model.data.AppState
import myapplication.repository.parseOnlineSearchResults
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.scope.Scope
import q4_android_professional.myapplication.interactor.MainInterActor

class MainViewModel(
    private val interActor: MainInterActor
) : BaseViewModel<AppState>(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }

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

    private suspend fun startInterActor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(
                parseOnlineSearchResults(
                    interActor.getData(
                        word,
                        isOnline
                    )
                )
            )
        }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}