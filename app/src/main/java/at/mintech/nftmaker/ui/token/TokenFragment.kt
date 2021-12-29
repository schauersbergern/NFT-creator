package at.mintech.nftmaker.ui.token

import androidx.fragment.app.Fragment
import at.mintech.nftmaker.R
import at.mintech.nftmaker.databinding.TokenFragmentBinding
import at.mintech.nftmaker.helper.delegates.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TokenFragment : Fragment(R.layout.token_fragment) {

    private val viewModel by viewModel<TokenViewModel>()
    private val binding by viewBinding(TokenFragmentBinding::bind)

    companion object {
        fun newInstance() = TokenFragment()
    }
}