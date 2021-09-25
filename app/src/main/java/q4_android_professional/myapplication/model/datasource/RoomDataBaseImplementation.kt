package q4_android_professional.myapplication.model.datasource

import io.reactivex.Observable
import q4_android_professional.myapplication.model.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
// RX:   override fun getData(word: String): Observable<List<DataModel>> {
//        TODO("not implemented")
//    }
    /** Coroutines -  */
       override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented")
    }
}
