package q4_android_professional.myapplication.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import q4_android_professional.myapplication.R
import q4_android_professional.myapplication.databinding.FragmentMainBinding
import q4_android_professional.myapplication.viewModel.MainViewModel
import q4_android_professional.myapplication.viewModel.MainViewModelFactory

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var model: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMainBinding.inflate(inflater, container, false).also {
        _binding = it

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniViewModel()

        binding.buttonStart.setOnClickListener {
            model.buttonStart()
        }
        binding.buttonPause.setOnClickListener {
            model.buttonPause()
        }
        binding.buttonStop.setOnClickListener {
            model.buttonStop()

        }

        binding.textTime.text = getText(R.string._00_00_000)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun iniViewModel() {
        model = ViewModelProvider(
            this, MainViewModelFactory()
        ).get(MainViewModel::class.java)

        model.liveData.observe(viewLifecycleOwner, { data -> binding.textTime.text = data })
    }
}