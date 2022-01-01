package at.mintech.nftmaker.ui.token

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import at.mintech.nftmaker.R
import at.mintech.nftmaker.databinding.TokenFragmentBinding
import at.mintech.nftmaker.helper.delegates.viewBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class TokenFragment : Fragment(R.layout.token_fragment) {

    private val viewModel by viewModel<TokenViewModel>()
    private val binding by viewBinding(TokenFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    companion object {
        fun newInstance() = TokenFragment()
    }
}