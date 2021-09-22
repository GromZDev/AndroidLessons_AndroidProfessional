package q4_android_professional.myapplication.di

import dagger.Module
import dagger.Provides
import q4_android_professional.myapplication.interactor.MainInterActor
import q4_android_professional.myapplication.model.DataModel
import q4_android_professional.myapplication.repository.Repository
import javax.inject.Named

@Module
class InterActorModule {

    @Provides
    fun provideInterActor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInterActor(repositoryRemote, repositoryLocal)
}