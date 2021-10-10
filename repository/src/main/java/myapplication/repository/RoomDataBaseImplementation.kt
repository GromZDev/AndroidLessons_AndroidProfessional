package myapplication.repository

import myapplication.model.data.AppState
import myapplication.model.data.DataModel
import myapplication.repository.repolocal.DataSourceLocal
import myapplication.repository.room.HistoryDao

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
