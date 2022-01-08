package at.mintech.nftmaker.ui.token

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import at.mintech.nftmaker.R
import at.mintech.nftmaker.databinding.LoadingIndicatorBinding
import at.mintech.nftmaker.databinding.TokenFragmentBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class TokenFragment : Fragment(R.layout.token_fragment) {

    private val viewModel by viewModel<TokenViewModel>()
    private var _binding: TokenFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var loadingIndicator : LoadingIndicatorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TokenFragmentBinding.inflate(inflater, container, false)
        loadingIndicator = LoadingIndicatorBinding.bind(binding.root)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getTokenButton.setOnClickListener {
            viewModel.obtainTokens(5)
        }

        observeState()
        observeEvents()
        viewModel.init()
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

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.container.sideEffectFlow.collect {
                when (it) {
                    TokenSideEffects.ContentLoading -> showLoading()
                    TokenSideEffects.ContentLoaded -> hideLoading()
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

    companion object {
        fun newInstance() = TokenFragment()
    }
}