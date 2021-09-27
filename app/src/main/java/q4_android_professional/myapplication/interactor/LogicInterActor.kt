package q4_android_professional.myapplication.interactor


/** Тут чистая логика */
interface LogicInterActor<T> {

    // RX: fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
    /** Coroutines -  */
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}