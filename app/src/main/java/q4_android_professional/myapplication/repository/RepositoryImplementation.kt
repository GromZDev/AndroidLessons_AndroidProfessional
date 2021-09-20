package q4_android_professional.myapplication.repository

import io.reactivex.Observable
import q4_android_professional.myapplication.model.datasource.DataSource
import q4_android_professional.myapplication.model.DataModel

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}