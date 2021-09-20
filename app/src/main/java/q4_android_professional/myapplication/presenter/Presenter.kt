package q4_android_professional.myapplication.presenter

import q4_android_professional.myapplication.view.base.View
import q4_android_professional.myapplication.model.AppState

/** // Презентер, который ничего не знает ни о контексте,
 *  ни о фреймворке */
interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)
    fun detachView(view: V)
    fun getData(word: String, isOnline: Boolean)

}