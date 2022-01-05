package at.mintech.nftmaker.ui.displayNfts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import at.mintech.nftmaker.R
import at.mintech.nftmaker.databinding.DisplayNftsFragmentBinding
import at.mintech.nftmaker.helper.delegates.viewBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class DisplayNftsFragment : Fragment(R.layout.display_nfts_fragment) {

    private val viewModel by viewModel<DisplayNftsViewModel>()
    private val binding by viewBinding(DisplayNftsFragmentBinding::bind)

    companion object {
        fun newInstance() = DisplayNftsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        observeEvents()
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
            }
        }
    }

}