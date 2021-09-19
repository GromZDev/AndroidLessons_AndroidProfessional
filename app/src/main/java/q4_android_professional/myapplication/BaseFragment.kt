package q4_android_professional.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : AppState> : Fragment(), View {

    protected lateinit var presenter: Presenter<T, View>
    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }
    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }
    /**  При пересоздании или уничтожении View удаляем ссылку, иначе в презентере
// будет ссылка на несуществующую View */
    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

}