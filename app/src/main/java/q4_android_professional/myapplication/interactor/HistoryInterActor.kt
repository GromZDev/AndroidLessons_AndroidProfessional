package q4_android_professional.myapplication.interactor

import myapplication.core.viewmodel.LogicInterActor
import myapplication.model.data.AppState
import myapplication.model.data.DataModel
import q4_android_professional.myapplication.model.datasource.roomlocal.RepositoryLocal
import q4_android_professional.myapplication.repository.Repository

class HistoryInterActor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : LogicInterActor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
