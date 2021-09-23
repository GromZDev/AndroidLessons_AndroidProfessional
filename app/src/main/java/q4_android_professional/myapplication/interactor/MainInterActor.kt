package q4_android_professional.myapplication.interactor

import io.reactivex.Observable
import q4_android_professional.myapplication.di.NAME_LOCAL
import q4_android_professional.myapplication.di.NAME_REMOTE
import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.model.DataModel
import q4_android_professional.myapplication.repository.Repository
import javax.inject.Inject
import javax.inject.Named

class MainInterActor @Inject constructor(

    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
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