package q4_android_professional.myapplication.interactor

import myapplication.core.viewmodel.LogicInterActor
import myapplication.model.data.AppState
import myapplication.model.data.DataModel
import q4_android_professional.myapplication.model.datasource.roomlocal.RepositoryLocal
import q4_android_professional.myapplication.repository.Repository

class MainInterActor(

    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : LogicInterActor<AppState> {


    /** Coroutines -  */
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {

        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word))
        }
        return appState

    }
}