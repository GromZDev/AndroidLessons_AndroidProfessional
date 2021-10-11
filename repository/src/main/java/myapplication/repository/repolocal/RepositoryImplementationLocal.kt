package myapplication.repository.repolocal

import myapplication.model.data.AppState
import myapplication.model.data.dto.SearchResultDTO

class RepositoryImplementationLocal(
    private val dataSource: DataSourceLocal<List<SearchResultDTO>>
) : RepositoryLocal<List<SearchResultDTO>> {

    override suspend fun getData(word: String): List<SearchResultDTO> = dataSource.getData(word)

    override suspend fun saveToDB(appState: AppState) = dataSource.saveToDB(appState)

}