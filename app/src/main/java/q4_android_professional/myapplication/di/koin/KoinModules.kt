package q4_android_professional.myapplication.di.koin

import org.koin.core.qualifier.named
import org.koin.dsl.module
import q4_android_professional.myapplication.di.NAME_LOCAL
import q4_android_professional.myapplication.di.NAME_REMOTE
import q4_android_professional.myapplication.interactor.MainInterActor
import q4_android_professional.myapplication.model.DataModel
import q4_android_professional.myapplication.model.datasource.RetrofitImplementation
import q4_android_professional.myapplication.model.datasource.RoomDataBaseImplementation
import q4_android_professional.myapplication.repository.Repository
import q4_android_professional.myapplication.repository.RepositoryImplementation
import q4_android_professional.myapplication.viewmodel.MainViewModel


/**
 * module { } — создание модуля, это контейнер для коллекции зависимостей
 * single { } — генерация синглтона
 * factory { } — генерация зависимости каждый раз заново
 * get() — создание экземпляра класса
 * */

/** application - тут хранятся зависимости, используемые во всем приложении */
val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

/** mainScreen - тут хранятся зависимости, используемые на конкретном экране */
val mainScreen = module {
    factory { MainInterActor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
