package q4_android_professional.myapplication.repository

import q4_android_professional.myapplication.model.DataModel
import q4_android_professional.myapplication.model.datasource.retrofitremote.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    /** Coroutines -  */
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}