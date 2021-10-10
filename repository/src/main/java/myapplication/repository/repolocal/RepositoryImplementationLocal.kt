package myapplication.repository.repolocal

import myapplication.model.data.AppState
import myapplication.model.data.DataModel

class RepositoryImplementationLocal(
    private val dataSource: DataSourceLocal<List<DataModel>>
) : RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = dataSource.getData(word)

    override suspend fun saveToDB(appState: AppState) = dataSource.saveToDB(appState)

}