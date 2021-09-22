package q4_android_professional.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.reactive.SchedulerProvider

abstract class BaseViewModel<T : AppState>(
    protected val livedataToObserve: MutableLiveData<T> =
        MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable =
        CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider =
        SchedulerProvider()
) : ViewModel() {

    open fun getData(word: String, isOnline: Boolean): LiveData<T> =
        livedataToObserve

    override fun onCleared() {
        compositeDisposable.clear()
    }

}