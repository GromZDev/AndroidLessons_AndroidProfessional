package q4_android_professional.myapplication

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import q4_android_professional.myapplication.di.koin.application
import q4_android_professional.myapplication.di.koin.historyScreen
import q4_android_professional.myapplication.di.koin.mainScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}