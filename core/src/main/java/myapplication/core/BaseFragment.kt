package myapplication.core

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import myapplication.core.viewmodel.BaseViewModel
import myapplication.core.viewmodel.LogicInterActor
import myapplication.model.data.AppState
import myapplication.model.data.data.DataModel
import myapplication.utils.networkstatus.AlertDialogFragment
import myapplication.utils.networkstatus.OnlineLiveData

abstract class BaseFragment<T : AppState, I : LogicInterActor<T>> : Fragment() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

    /** Проверка состояния сети ============================= */
    protected var isNetworkAvailable: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToNetworkChange()
    }

    private fun subscribeToNetworkChange() {
        context?.let { net ->
            OnlineLiveData(net).observe(
                viewLifecycleOwner,
                {
                    isNetworkAvailable = it
                    if (!isNetworkAvailable) {
                        Toast.makeText(
                            context,
                            R.string.dialog_message_device_is_offline,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    private fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    private fun isDialogNull(): Boolean {
        return childFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    private fun showAlertDialog(title: String?, message: String?) {
        childFragmentManager.let {
            AlertDialogFragment.newInstance(title, message).show(it, DIALOG_FRAGMENT_TAG)
        }
    }

    abstract fun setDataToAdapter(data: List<DataModel>)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "Network_detecting_TAG_999"
    }
}