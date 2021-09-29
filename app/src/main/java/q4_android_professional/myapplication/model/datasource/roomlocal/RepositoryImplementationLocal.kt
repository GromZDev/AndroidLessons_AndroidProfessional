package q4_android_professional.myapplication.model.datasource.roomlocal

import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.model.DataModel

class RepositoryImplementationLocal(
    private val dataSource: DataSourceLocal<List<DataModel>>
) : RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = dataSource.getData(word)

    override suspend fun saveToDB(appState: AppState) = dataSource.saveToDB(appState)

}