package q4_android_professional.myapplication

import io.reactivex.Observable

class MainInterActor(

    private val remoteRepository: Repository<List<DataModel>>,
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