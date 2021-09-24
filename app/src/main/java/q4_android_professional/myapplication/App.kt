package q4_android_professional.myapplication

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.koin.core.context.startKoin
import q4_android_professional.myapplication.di.DaggerApplicationComponent
import q4_android_professional.myapplication.di.koin.application
import q4_android_professional.myapplication.di.koin.mainScreen
import javax.inject.Inject

class App : Application()

// Закомментированный Dagger!!!
//    , HasAndroidInjector
        {

//    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
//
//    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }


//        DaggerApplicationComponent.builder()
//            .application(this)
//            .build()
//            .inject(this)
    }


}