package myapplication.repository.reporemote

/** Репозиторий представляет собой слой получения
 * и хранения данных, которые он передаёт интерактору */
interface Repository<T> {

    // RX: fun getData(word: String): Observable<T>
    /** Coroutines -  */
    suspend fun getData(word: String): T


}