package q4_android_professional.myapplication.interactor

import io.reactivex.Observable


/** Тут чистая логика */
interface LogicInterActor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>

}