package at.mintech.nftmaker.ui.createNft

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import at.mintech.nftmaker.R
import at.mintech.nftmaker.databinding.CreateNftFragmentBinding
import at.mintech.nftmaker.databinding.LoadingIndicatorBinding
import at.mintech.nftmaker.helper.config.SUPPORTED_FILE_TYPES
import at.mintech.nftmaker.helper.config.getFileType
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateNftFragment : Fragment(R.layout.create_nft_fragment) {

    private val viewModel by viewModel<CreateNftViewModel>()

    private var _binding: CreateNftFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var loadingIndicator : LoadingIndicatorBinding

    private val ipfsUploadListener : (View) -> Unit = {
        val intent = Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
        resultLauncher.launch(Intent.createChooser(intent, "Select a file"))
    }

    private val mintNftListener : (View) -> Unit = {
        viewModel.mintNft()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateNftFragmentBinding.inflate(inflater, container, false)
        loadingIndicator = LoadingIndicatorBinding.bind(binding.root)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    val fileType = uri.getFileType(requireContext())
                    if (isFileTypeSupported(fileType)) {
                        val stream = requireActivity().contentResolver.openInputStream(uri)
                        stream?.readBytes()?.let {
                            if (fileType != null) {
                                setPreviewImage(uri, fileType)
                                viewModel.uploadData(it, fileType)
                            }
                        }
                    } else {
                        showError(ERROR_FILE_TYPE)
                    }
                }
            }
        }

        observeState()
        observeEvents()
    }

    private fun setPreviewImage(uri : Uri, fileType : String) {
        when (fileType) {
            "pdf" -> binding.nftView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_preview_pdf))
            "mp3", "m4a" -> binding.nftView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_preview_audio))
            "mp4" -> binding.nftView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_preview_video))
            "jpeg", "jpg", "png" -> binding.nftView.setImageBitmap(getImageBitmap(uri))
        }
    }

    private fun getImageBitmap(uri : Uri) : Bitmap {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver, uri))
        } else {
            MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
        }
    }

    private fun isFileTypeSupported(fileType : String?) : Boolean {
        return if (fileType != null) {
            SUPPORTED_FILE_TYPES.contains(fileType)
        } else {
            false
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.container.stateFlow.collect { state ->
                if (state.nftUrl != "") {
                    uiForMinting()
                } else {
                    uiForIpfsUpload()
                }
            }
        }
    }

    private fun uiForIpfsUpload() {
        binding.nftView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.nft))
        binding.uploadButton.setText(R.string.ipfs_upload)
        binding.uploadButton.setOnClickListener(ipfsUploadListener)
    }

    private fun uiForMinting() {
        binding.uploadButton.setText(R.string.mint_nft)
        binding.uploadButton.setOnClickListener(mintNftListener)
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.container.sideEffectFlow.collect { sideEffect ->
                when(sideEffect) {
                    CreateNftViewModelSideEffects.ContentLoaded -> hideLoading()
                    CreateNftViewModelSideEffects.Loading -> showLoading()
                    is CreateNftViewModelSideEffects.ShowError -> showError(sideEffect.error)
                }
            }
        }
    }

    private fun hideLoading() {
        loadingIndicator.progressWrapper.visibility = View.GONE
    }

    private fun showLoading() {
        loadingIndicator.progressWrapper.visibility = View.VISIBLE
    }

    private fun showError(error: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(error)
            .setNegativeButton(ERROR_BTN_TEXT, null)
            .show()
    }

    companion object {
        fun newInstance() = CreateNftFragment()
        const val ERROR_BTN_TEXT = "OK"
        const val ERROR_FILE_TYPE = "This file type is not supported"
    }

}