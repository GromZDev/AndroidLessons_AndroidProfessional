package q4_android_professional.myapplication

import io.reactivex.Observable


/** Источник данных для репозитория (Интернет, БД и т. п.) */
interface DataSource<T> {

    fun getData(word: String): Observable<T>

}