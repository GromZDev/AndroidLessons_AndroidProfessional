package q4_android_professional.myapplication.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import q4_android_professional.myapplication.view.base.View
import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.model.datasource.DataSourceLocal
import q4_android_professional.myapplication.model.datasource.DataSourceRemote
import q4_android_professional.myapplication.reactive.SchedulerProvider
import q4_android_professional.myapplication.repository.RepositoryImplementation

class MainPresenterImpl<T : AppState, V : View>(

    private val interActor: MainInterActor = MainInterActor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),

    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {


    private var currentView: V? = null


    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interActor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}