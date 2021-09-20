package q4_android_professional.myapplication.api


import io.reactivex.Observable
import q4_android_professional.myapplication.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}