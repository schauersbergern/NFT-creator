package at.mintech.nftmaker

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import at.mintech.nftmaker.databinding.MainActivityBinding
import at.mintech.nftmaker.ui.createNft.CreateNftFragment
import at.mintech.nftmaker.ui.displayNfts.DisplayNftsFragment
import at.mintech.nftmaker.ui.token.TokenFragment

class MainActivity : FragmentActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}