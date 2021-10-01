package q4_android_professional.myapplication.di.koin

import androidx.room.Room
import myapplication.model.data.DataModel
import org.koin.dsl.module
import q4_android_professional.myapplication.interactor.MainInterActor
import q4_android_professional.myapplication.model.datasource.roomlocal.RepositoryImplementationLocal
import q4_android_professional.myapplication.model.datasource.roomlocal.RepositoryLocal
import q4_android_professional.myapplication.model.datasource.retrofitremote.RetrofitImplementation
import q4_android_professional.myapplication.model.datasource.roomlocal.RoomDataBaseImplementation
import q4_android_professional.myapplication.repository.Repository
import q4_android_professional.myapplication.repository.RepositoryImplementation
import q4_android_professional.myapplication.room.HistoryDataBase
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

