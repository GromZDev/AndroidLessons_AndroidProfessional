package q4_android_professional.myapplication.repository

/** Репозиторий представляет собой слой получения
 * и хранения данных, которые он передаёт интерактору */
interface Repository<T> {

    // RX: fun getData(word: String): Observable<T>
    /** Coroutines -  */
    suspend fun getData(word: String): T


}