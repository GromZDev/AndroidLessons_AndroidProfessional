package q4_android_professional.myapplication.model.datasource.roomlocal

import myapplication.model.data.AppState
import myapplication.model.data.DataModel
import q4_android_professional.myapplication.room.HistoryDao
import q4_android_professional.myapplication.utils.networkstatus.convertDataModelSuccessToEntity
import q4_android_professional.myapplication.utils.networkstatus.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {
    /** Coroutines -  */
       override suspend fun getData(word: String): List<DataModel> =
           mapHistoryEntityToSearchResult(historyDao.all())

    /** Сохраняем слово в БД для интерактора */
    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
