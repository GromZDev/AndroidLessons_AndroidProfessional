package q4_android_professional.myapplication.interactor

import myapplication.core.viewmodel.LogicInterActor
import myapplication.model.data.AppState
import myapplication.model.data.dto.SearchResultDTO
import myapplication.repository.mapSearchResultToResult
import myapplication.repository.repolocal.RepositoryLocal
import myapplication.repository.reporemote.Repository

class MainInterActor(

    private val remoteRepository: Repository<List<SearchResultDTO>>,
    private val localRepository: RepositoryLocal<List<SearchResultDTO>>
) : LogicInterActor<AppState> {


    /** Coroutines -  */
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {

        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(remoteRepository.getData(word)))
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(localRepository.getData(word)))
        }
        return appState

    }
}