package q4_android_professional.myapplication.di.koin

import androidx.room.Room
import myapplication.historyscreen.HistoryFragment
import myapplication.historyscreen.HistoryInterActor
import myapplication.historyscreen.HistoryViewModel
import myapplication.model.data.DataModel
import myapplication.repository.RetrofitImplementation
import myapplication.repository.RoomDataBaseImplementation
import myapplication.repository.repolocal.RepositoryImplementationLocal
import myapplication.repository.repolocal.RepositoryLocal
import myapplication.repository.reporemote.Repository
import myapplication.repository.reporemote.RepositoryImplementation
import myapplication.repository.room.HistoryDataBase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import q4_android_professional.myapplication.interactor.MainInterActor
import q4_android_professional.myapplication.view.main.MainFragment
import q4_android_professional.myapplication.viewmodel.MainViewModel

/** application - тут хранятся зависимости, используемые во всем приложении */
val application = module {

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

/** Объявляем скоуп для главного фрагмента. Также и viewModel */
val mainScreen = module {
    scope(named<MainFragment>()) {
        scoped { MainInterActor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

/** Объявляем скоуп для фрагмента истории. Также и viewModel */
val historyScreen = module {
    scope(named<HistoryFragment>()) {
        scoped { HistoryInterActor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}

