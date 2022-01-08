package at.mintech.nftmaker.ui.displayNfts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import at.mintech.nftmaker.R
import at.mintech.nftmaker.databinding.DisplayNftsFragmentBinding
import at.mintech.nftmaker.databinding.LoadingIndicatorBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class DisplayNftsFragment : Fragment(R.layout.display_nfts_fragment) {

    private val viewModel by viewModel<DisplayNftsViewModel>()
    private var _binding: DisplayNftsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var loadingIndicator : LoadingIndicatorBinding

    companion object {
        fun newInstance() = DisplayNftsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DisplayNftsFragmentBinding.inflate(inflater, container, false)
        loadingIndicator = LoadingIndicatorBinding.bind(binding.root)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        observeEvents()
        viewModel.fetchNfts()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.container.stateFlow.collect { state ->
                if (state.displayNfts.isNotEmpty()) {
                    binding.nftlist.adapter = NftListAdapter(state.displayNfts)
                }
            }
        }
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.container.sideEffectFlow.collect {
                when (it) {
                    DisplayNftsSideEffects.ContentLoading -> showLoading()
                    DisplayNftsSideEffects.ContentLoaded -> hideLoading()
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

}