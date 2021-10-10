package myapplication.historyscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import myapplication.core.BaseFragment
import myapplication.historyscreen.databinding.FragmentHistoryBinding
import myapplication.model.data.AppState
import myapplication.model.data.DataModel
import org.koin.android.compat.ViewModelCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<AppState, HistoryInterActor>() {

    override lateinit var model: HistoryViewModel

    private val observer = Observer<AppState> {
        renderData(it)
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    //  private var adapter: HistoryAdapter? = null

    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHistoryBinding.inflate(inflater, container, false).also {
        _binding = it
        iniViewModel()
        initViews()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getData("price", false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    binding.historyActivityRecyclerview.let { adapter.setData(dataModel) }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontalHistory.visibility = View.VISIBLE
                    binding.progressBarRoundHistory.visibility = View.GONE
                    binding.progressBarHorizontalHistory.progress = appState.progress!!
                } else {
                    binding.progressBarHorizontalHistory.visibility = View.GONE
                    binding.progressBarRoundHistory.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextviewHistory.text = error ?: getString(R.string.undefined_error)
        binding.reloadButtonHistory.setOnClickListener {

        }
    }

    private fun showViewSuccess() {
        binding.workingFrameLayout.visibility = View.VISIBLE
        binding.loadingFrameLayoutHistory.visibility = View.GONE
        binding.errorLinearLayoutHistory.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.workingFrameLayout.visibility = View.GONE
        binding.loadingFrameLayoutHistory.visibility = View.VISIBLE
        binding.errorLinearLayoutHistory.visibility = View.GONE
    }

    private fun showViewError() {
        binding.workingFrameLayout.visibility = View.GONE
        binding.loadingFrameLayoutHistory.visibility = View.GONE
        binding.errorLinearLayoutHistory.visibility = View.VISIBLE
    }

    /** Реализация Koin ========================== */
    private fun initViews() {
        binding.historyActivityRecyclerview.layoutManager =
            LinearLayoutManager(context)
        binding.historyActivityRecyclerview.adapter = adapter
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("ViewModel должна быть сначала инициирована!")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(viewLifecycleOwner, observer)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }
    /** ============================================= */

}