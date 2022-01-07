package at.mintech.nftmaker.ui.bottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.mintech.nftmaker.R
import at.mintech.nftmaker.databinding.BottomNavigationFragmentBinding
import at.mintech.nftmaker.ui.createNft.CreateNftFragment
import at.mintech.nftmaker.ui.displayNfts.DisplayNftsFragment
import at.mintech.nftmaker.ui.token.TokenFragment

class BottomNavigationFragment : Fragment() {

    private var _binding: BottomNavigationFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = BottomNavigationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val createNftFragment = CreateNftFragment.newInstance()
        val displayNftsFragment = DisplayNftsFragment.newInstance()
        val tokenFragment = TokenFragment.newInstance()

        setCurrentFragment(createNftFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when( it.itemId ) {
                R.id.upload_nft->setCurrentFragment(createNftFragment)
                R.id.my_nfts->setCurrentFragment(displayNftsFragment)
                R.id.token_demo->setCurrentFragment(tokenFragment)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}