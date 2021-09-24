package q4_android_professional.myapplication.interactor

import io.reactivex.Observable
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

    override fun getData(word: String, fromRemoteSource: Boolean):
            Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}