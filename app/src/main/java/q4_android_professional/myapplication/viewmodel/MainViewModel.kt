package q4_android_professional.myapplication.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import q4_android_professional.myapplication.interactor.MainInterActor
import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.utils.networkstatus.parseSearchResults

class MainViewModel
// Закомментированный Dagger!!!
//@Inject constructor
    (
    private val interActor: MainInterActor
) : BaseViewModel<AppState>() {

    // private var appState: AppState? = null

    private val livedataToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return livedataToObserve
    }

// RX    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
//        compositeDisposable.add(
//            interActor.getData(word, isOnline)
//                .subscribeOn(schedulerProvider.io())
//                .observeOn(schedulerProvider.ui())
//                .doOnSubscribe {
//                    livedataToObserve.value =
//                        AppState.Loading(null)
//                }
//                .subscribeWith(getObserver())
//        )
//        return super.getData(word, isOnline)
//    }

    /** Coroutines -  */
    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch { startInterActor(word, isOnline) }
    }

    private suspend fun startInterActor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(
                parseSearchResults(
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

// RX   private fun getObserver(): DisposableObserver<AppState> {
//        return object : DisposableObserver<AppState>() {
//            override fun onNext(state: AppState) {
//                appState = parseSearchResults(state)
//                livedataToObserve.postValue(appState)
//            }
//
//            override fun onError(e: Throwable) {
//                livedataToObserve.postValue(AppState.Error(e))
//            }
//
//            override fun onComplete() {
//
//            }
//        }
//    }

}