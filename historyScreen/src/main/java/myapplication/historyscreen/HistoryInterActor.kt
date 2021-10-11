package myapplication.historyscreen

import myapplication.core.viewmodel.LogicInterActor
import myapplication.model.data.AppState
import myapplication.model.data.dto.SearchResultDTO
import myapplication.repository.mapSearchResultToResult
import myapplication.repository.repolocal.RepositoryLocal
import myapplication.repository.reporemote.Repository

class HistoryInterActor(
    private val repositoryRemote: Repository<List<SearchResultDTO>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDTO>>
) : LogicInterActor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppState {
        return AppState.Success(mapSearchResultToResult(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
        )
    }
}
