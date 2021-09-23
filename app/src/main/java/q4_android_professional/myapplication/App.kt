package q4_android_professional.myapplication

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import q4_android_professional.myapplication.di.DaggerApplicationComponent
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }


}