package at.mintech.nftmaker.ui.displayNfts

import androidx.fragment.app.Fragment
import at.mintech.nftmaker.R
import at.mintech.nftmaker.databinding.DisplayNftsFragmentBinding
import at.mintech.nftmaker.helper.delegates.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DisplayNftsFragment : Fragment(R.layout.display_nfts_fragment) {

    private val viewModel by viewModel<DisplayNftsViewModel>()
    private val binding by viewBinding(DisplayNftsFragmentBinding::bind)

    companion object {
        fun newInstance() = DisplayNftsFragment()
    }
}