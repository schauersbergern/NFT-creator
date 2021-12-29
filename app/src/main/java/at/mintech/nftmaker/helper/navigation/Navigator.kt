package at.mintech.nftmaker.helper.navigation

import androidx.fragment.app.FragmentManager
import at.mintech.nftmaker.R
import at.mintech.nftmaker.ui.createNft.CreateNftFragment
import at.mintech.nftmaker.ui.scan.ScanFragment
import at.mintech.nftmaker.ui.splash.SplashFragment

class Navigator {
    companion object {
        fun showSplashFragment(fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance())
                .commitNow()
        }

        fun showScanFragment(fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(R.id.container, ScanFragment.newInstance())
                .commitNow()
        }

        fun showNftFragment(fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(R.id.container, CreateNftFragment.newInstance())
                .commitNow()
        }
    }
}