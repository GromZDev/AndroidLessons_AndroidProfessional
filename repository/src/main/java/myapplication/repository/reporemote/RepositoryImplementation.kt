package myapplication.repository.reporemote

import myapplication.model.data.dto.SearchResultDTO

class RepositoryImplementation(private val dataSource: DataSource<List<SearchResultDTO>>) :
    Repository<List<SearchResultDTO>> {

    /** Coroutines -  */
    override suspend fun getData(word: String): List<SearchResultDTO> {
        return dataSource.getData(word)
    }
}