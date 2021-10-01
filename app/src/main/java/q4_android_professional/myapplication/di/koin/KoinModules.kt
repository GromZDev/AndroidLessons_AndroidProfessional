package q4_android_professional.myapplication.di.koin

import androidx.room.Room
import myapplication.model.data.DataModel
import myapplication.repository.RetrofitImplementation
import myapplication.repository.RoomDataBaseImplementation
import myapplication.repository.repolocal.RepositoryImplementationLocal
import myapplication.repository.repolocal.RepositoryLocal
import myapplication.repository.reporemote.Repository
import myapplication.repository.reporemote.RepositoryImplementation
import myapplication.repository.room.HistoryDataBase
import org.koin.dsl.module
import q4_android_professional.myapplication.interactor.MainInterActor
import q4_android_professional.myapplication.interactor.HistoryInterActor
import q4_android_professional.myapplication.viewmodel.HistoryViewModel
import q4_android_professional.myapplication.viewmodel.MainViewModel


/**
 * module { } — создание модуля, это контейнер для коллекции зависимостей
 * single { } — генерация синглтона
 * factory { } — генерация зависимости каждый раз заново
 * get() — создание экземпляра класса
 * */

/** application - тут хранятся зависимости, используемые во всем приложении */
val application = module {

    /** БД должна быть в единственном экземпляре */
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    /** Получаем DAO */
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInterActor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInterActor(get(), get()) }
}

