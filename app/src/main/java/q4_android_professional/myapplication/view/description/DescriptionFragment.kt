package q4_android_professional.myapplication.view.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import myapplication.model.data.data.DataModel
import myapplication.repository.convertMeaningsToString
import myapplication.repository.convertNoteToString
import myapplication.utils.EquilateralImageView
import myapplication.utils.networkstatus.AlertDialogFragment
import myapplication.utils.networkstatus.OnlineLiveData
import q4_android_professional.myapplication.R
import q4_android_professional.myapplication.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "DESCRIPTION_DIALOG_TAG"
        private const val BUNDLE_EXTRA = "MY_Word_Description"
        fun newInstance(data: DataModel) = DescriptionFragment().apply {
            arguments = bundleOf(BUNDLE_EXTRA to data)
        }
    }

    private val data: DataModel by lazy {
        arguments?.getParcelable<DataModel>("MY_Word_Description") as DataModel
    }

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDescriptionBinding.inflate(inflater, container, false).also {
        _binding = it

    }.root

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startLoadingOrShowError()
        //  setData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setData() {

        binding.descriptionHeader.text = data.text
        binding.descriptionTextview.text = convertMeaningsToString(data.meanings)
        binding.transcriptionTextview.text = data.meanings[0].transcriptionNew
        binding.noteTextview.text = convertNoteToString(data.meanings)
        val imageLink = data.meanings[0].imageUrlNew

        usePicassoToLoadPhoto(binding.descriptionImageview, imageLink)
    }

    private fun usePicassoToLoadPhoto(imageView: EquilateralImageView, imageLink: String) {
        Picasso.get().load("https:$imageLink")
            .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            })
    }

    private fun startLoadingOrShowError() {
        val manager = activity?.supportFragmentManager
        context?.let { net ->
            OnlineLiveData(net).observe(
                viewLifecycleOwner,
                {
                    if (it) {
                        setData()
                    } else {
                        if (manager != null) {
                            AlertDialogFragment.newInstance(
                                getString(R.string.dialog_title_device_is_offline),
                                getString(R.string.dialog_message_device_is_offline)
                            ).show(
                                manager,
                                DIALOG_FRAGMENT_TAG
                            )
                        }
                    }
                })
        }
    }
}















