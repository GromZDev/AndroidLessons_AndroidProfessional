package q4_android_professional.myapplication.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import q4_android_professional.myapplication.R
import q4_android_professional.myapplication.model.AppState
import q4_android_professional.myapplication.utils.networkstatus.AlertDialogFragment
import q4_android_professional.myapplication.utils.networkstatus.isOnline
import q4_android_professional.myapplication.viewmodel.BaseViewModel

abstract class BaseFragment<T: AppState>: Fragment() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

    /** Проверка состояния сети ============================= */
    protected var isNetworkAvailable: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isNetworkAvailable = context?.let { isOnline(it) } == true
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = context?.let { isOnline(it) } == true
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    private fun isDialogNull(): Boolean {
        return fragmentManager?.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        fragmentManager?.let { AlertDialogFragment.newInstance(title, message).show(it, DIALOG_FRAGMENT_TAG) }
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "Network_detecting_TAG_999"
    }
    /** =========================================================== */
}