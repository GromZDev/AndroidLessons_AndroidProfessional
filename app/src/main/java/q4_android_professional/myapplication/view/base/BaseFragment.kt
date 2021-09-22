package q4_android_professional.myapplication.view.base

import androidx.fragment.app.Fragment
import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.viewmodel.BaseViewModel

abstract class BaseFragment<T: AppState>: Fragment() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)
}