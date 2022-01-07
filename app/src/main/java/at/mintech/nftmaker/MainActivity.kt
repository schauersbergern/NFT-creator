package at.mintech.nftmaker

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import at.mintech.nftmaker.databinding.MainActivityBinding
import at.mintech.nftmaker.helper.navigation.Navigator
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : FragmentActivity() {

    private val viewModel by viewModel<StartViewModel>()
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Navigator.showSplashFragment(supportFragmentManager)
        observeEvents()
        viewModel.getAddress()
    }

    private fun observeEvents() {
        lifecycleScope.launchWhenCreated {
            viewModel.container.sideEffectFlow.collect {
                when (it) {
                    StartSideEffect.StartScan -> {
                        Navigator.showScanFragment(supportFragmentManager)
                    }
                    StartSideEffect.StartNFT -> {
                        Navigator.showBottomNavigationFragment(supportFragmentManager)
                    }
                }
            }
        }
    }

}