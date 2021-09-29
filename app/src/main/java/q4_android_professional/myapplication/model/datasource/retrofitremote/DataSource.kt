package q4_android_professional.myapplication.model.datasource.retrofitremote


/** Источник данных для репозитория (Интернет, БД и т. п.) */
interface DataSource<T> {

    // RX:  fun getData(word: String): Observable<T>
    /** Coroutines -  */
    suspend fun getData(word: String): T

}