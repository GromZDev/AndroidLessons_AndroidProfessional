package q4_android_professional.myapplication.api


import kotlinx.coroutines.Deferred
import q4_android_professional.myapplication.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    // RX: fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
    /** Coroutines - возвращаем данные через Deferred */
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}