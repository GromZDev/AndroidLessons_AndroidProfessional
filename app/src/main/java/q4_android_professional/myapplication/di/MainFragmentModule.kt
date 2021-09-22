package q4_android_professional.myapplication.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import q4_android_professional.myapplication.view.main.MainFragment

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}