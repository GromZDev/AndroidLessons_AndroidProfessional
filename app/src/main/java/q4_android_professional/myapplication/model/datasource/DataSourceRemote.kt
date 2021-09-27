package q4_android_professional.myapplication.model.datasource

import io.reactivex.Observable
import q4_android_professional.myapplication.model.DataModel

/** Открыть класс для RX! */
// Для получения внешних данных мы будем использовать Retrofit
//class DataSourceRemote(private val remoteProvider: RetrofitImplementation =
//                           RetrofitImplementation()
//) :
//    DataSource<List<DataModel>> {
//    override fun getData(word: String): Observable<List<DataModel>> =
//        remoteProvider.getData(word)
//}
//// Для локальных данных используется Room
//class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation =
//                          RoomDataBaseImplementation()
//) :
//    DataSource<List<DataModel>> {
//    override fun getData(word: String): Observable<List<DataModel>> =
//        remoteProvider.getData(word)
//}