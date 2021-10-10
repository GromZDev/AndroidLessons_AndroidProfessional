package q4_android_professional.myapplication.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import myapplication.core.BaseFragment
import myapplication.model.data.AppState
import myapplication.model.data.DataModel
import myapplication.utils.viewById
import org.koin.android.ext.android.getKoin
import org.koin.core.scope.Scope
import q4_android_professional.myapplication.R
import q4_android_professional.myapplication.databinding.FragmentMainBinding
import q4_android_professional.myapplication.interactor.MainInterActor
import q4_android_professional.myapplication.utils.GlideImageLoader
import q4_android_professional.myapplication.view.description.DescriptionFragment
import q4_android_professional.myapplication.viewmodel.MainViewModel

class MainFragment : BaseFragment<AppState, MainInterActor>() {

    override lateinit var model: MainViewModel
    private val scope: Scope = getKoin().createScope<MainFragment>()

    private val observer = Observer<AppState> {
        renderData(it)
    }

    companion object {
        fun newInstance() = MainFragment()
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "DIALOG_TAG"
    }

    /** Инициируем два ProgressBar через кастомный делегат */
    private val progressBarHorizontal by viewById<ProgressBar>(R.id.progress_bar_horizontal)
    private val progressBarRound by viewById<ProgressBar>(R.id.progress_bar_round)

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var adapter: MainAdapter? = null
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {

                val manager = activity?.supportFragmentManager
                manager?.let {
                    manager.beginTransaction()
                        .replace(R.id.fragment_container, DescriptionFragment.newInstance(data))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMainBinding.inflate(inflater, container, false).also {
        _binding = it
        iniViewModel()
        initViews()
    }.root

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :

                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    /** Сеть  */
                    if (isNetworkAvailable) {
                        model.getData(searchWord, isNetworkAvailable)
                    } else {
                        showNoInternetConnectionDialog()
                    }
                }
            })
            childFragmentManager.let { it1 ->
                searchDialogFragment.show(
                    it1,
                    BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /** Также закрываем наш скоуп когда уже не нужен */
    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {

                        binding.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel, GlideImageLoader())
                    } else {
                        binding.mainActivityRecyclerview.let { adapter!!.setData(dataModel) }
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    progressBarHorizontal.visibility = VISIBLE
                    progressBarRound.visibility = GONE
                    progressBarHorizontal.progress = appState.progress!!
                } else {
                    progressBarHorizontal.visibility = GONE
                    progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData("Welcome!", true)
            // RX .observe(viewLifecycleOwner, observer)

        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    /** Реализация Koin ========================== */
    private fun initViews() {
        binding.mainActivityRecyclerview.layoutManager =
            LinearLayoutManager(context)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    private fun iniViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }

        val viewModel: MainViewModel by scope.inject()
        model = viewModel
        model.subscribe().observe(viewLifecycleOwner, observer)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter?.setData(data)
    }
    /** ============================================= */

}