package q4_android_professional.myapplication.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import q4_android_professional.myapplication.App
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        InterActorModule::class,
        ViewModelModule::class,
        MainFragmentModule::class,
        RepositoryModule::class,
        ImageLoaderModule::class]
)

@Singleton
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(myApp: App)
}