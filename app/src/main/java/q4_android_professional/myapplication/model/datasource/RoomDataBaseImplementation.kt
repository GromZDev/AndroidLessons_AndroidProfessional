package q4_android_professional.myapplication.model.datasource

import io.reactivex.Observable
import q4_android_professional.myapplication.model.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}
