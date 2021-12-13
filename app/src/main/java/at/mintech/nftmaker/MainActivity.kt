package at.mintech.nftmaker

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import at.mintech.nftmaker.StartSideEffect.StartScan
import at.mintech.nftmaker.StartSideEffect.StartNFT
import at.mintech.nftmaker.helper.navigation.Navigator
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.flow.collect

class MainActivity : FragmentActivity() {

    private val viewModel by viewModel<StartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            Navigator.showSplashFragment(supportFragmentManager)
        }
    }

    override fun onStart() {
        super.onStart()
        observeSideEffects()
        viewModel.getAddress()
    }

    private fun observeSideEffects() {
        lifecycleScope.launchWhenCreated {
            viewModel.container.sideEffectFlow.collect {
                when (it) {
                    is StartScan -> Navigator.showScanFragment(supportFragmentManager)
                    is StartNFT -> Navigator.showNftFragment(supportFragmentManager)
                }
            }
        }
    }
}