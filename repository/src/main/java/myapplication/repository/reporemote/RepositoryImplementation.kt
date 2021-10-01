package myapplication.repository.reporemote

import myapplication.model.data.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    /** Coroutines -  */
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}