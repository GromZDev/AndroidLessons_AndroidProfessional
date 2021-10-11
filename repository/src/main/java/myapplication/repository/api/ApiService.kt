package myapplication.repository.api


import kotlinx.coroutines.Deferred
import myapplication.model.data.dto.SearchResultDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    // RX: fun search(@Query("search") wordToSearch: String): Observable<List<SearchResultDTO>>
            /** Coroutines - возвращаем данные через Deferred */
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<SearchResultDTO>>
}