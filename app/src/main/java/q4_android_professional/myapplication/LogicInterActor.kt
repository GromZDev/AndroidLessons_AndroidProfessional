package q4_android_professional.myapplication

import io.reactivex.Observable


/** Тут чистая логика */
interface LogicInterActor<T> {

    /** Use Case: получение данных для вывода на экран. Используем RxJava */
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>


}