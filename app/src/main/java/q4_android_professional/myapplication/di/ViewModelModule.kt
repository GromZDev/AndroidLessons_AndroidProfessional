package q4_android_professional.myapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import q4_android_professional.myapplication.viewmodel.MainViewModel

@Module(includes = [InterActorModule::class])
abstract class ViewModelModule {

//    @Binds
//    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKeys(MainViewModel::class)
//    abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}