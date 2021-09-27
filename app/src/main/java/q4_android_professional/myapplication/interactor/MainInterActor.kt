package q4_android_professional.myapplication.interactor

import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.model.DataModel
import q4_android_professional.myapplication.repository.Repository

class MainInterActor
// Закомментированный Dagger!!!
//@Inject constructor
    (
    //@Named(NAME_REMOTE)
    private val remoteRepository: Repository<List<DataModel>>,
    // @Named(NAME_LOCAL)
    private val localRepository: Repository<List<DataModel>>
) : LogicInterActor<AppState> {
// RX <DataModel>
// RX:    override fun getData(word: String, fromRemoteSource: Boolean):
//            Observable<AppState> {
//        return if (fromRemoteSource) {
//            remoteRepository.getData(word).map { AppState.Success(it) }
//        } else {
//            localRepository.getData(word).map { AppState.Success(it) }
//        }
//    }

    /** Coroutines -  */
    override suspend fun getData(word: String, fromRemoteSource: Boolean):
            AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}