package q4_android_professional.myapplication.viewmodel

import androidx.lifecycle.LiveData
import io.reactivex.observers.DisposableObserver
import q4_android_professional.myapplication.interactor.MainInterActor
import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.model.datasource.DataSourceLocal
import q4_android_professional.myapplication.model.datasource.DataSourceRemote
import q4_android_professional.myapplication.repository.RepositoryImplementation

class MainViewModel(
    private val interActor: MainInterActor = MainInterActor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    )
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interActor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe {
                    livedataToObserve.value =
                        AppState.Loading(null)
                }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                appState = state
                livedataToObserve.postValue(state)
            }

            override fun onError(e: Throwable) {
                livedataToObserve.postValue(AppState.Error(e))
            }

            override fun onComplete() {

            }
        }
    }

}