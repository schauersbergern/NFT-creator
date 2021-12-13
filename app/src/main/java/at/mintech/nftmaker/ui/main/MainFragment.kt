package at.mintech.nftmaker.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import at.mintech.nftmaker.R
import kotlinx.coroutines.flow.collect
import at.mintech.nftmaker.databinding.MainFragmentBinding
import at.mintech.nftmaker.helper.delegates.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModel<MainViewModel>()
    private val binding by viewBinding(MainFragmentBinding::bind)
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    val stream = requireActivity().contentResolver.openInputStream(uri)
                    stream?.readBytes()?.let {
                        viewModel.uploadData(it)
                    }
                }
            }
        }

        binding.uploadButton.setOnClickListener {
            val intent = Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
            resultLauncher.launch(Intent.createChooser(intent, "Select a file"))
        }

        binding.getTokenButton.setOnClickListener {
            viewModel.obtainTokens(200)
        }

        observeState()
        viewModel.getTotalSupply()
        viewModel.getCreatorBalance()
        viewModel.getReceiverBalance()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.container.stateFlow.collect { state ->
                binding.tokenAmount.text = state.totalSupply.toString()
                binding.creatorTokenAmount.text = state.creatorAccountBalance.toString()
                binding.receiverTokenAmount.text = state.receiverAccountBalance.toString()
            }
        }
    }

}