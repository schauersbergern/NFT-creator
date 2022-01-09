package at.mintech.nftmaker.helper.navigation

import at.mintech.nftmaker.R
import at.mintech.nftmaker.ui.bottomNavigation.BottomNavigationFragment
import at.mintech.nftmaker.ui.scan.ScanFragment
import at.mintech.nftmaker.ui.splash.SplashFragment
import androidx.fragment.app.FragmentManager

class Navigator {
    companion object {
        fun showSplashFragment(fragmentManager: FragmentManager) =
            fragmentManager.beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance())
                .commit()


        fun showScanFragment(fragmentManager: FragmentManager) =
            fragmentManager.beginTransaction()
                .replace(R.id.container, ScanFragment.newInstance())
                .commit()


        fun showBottomNavigationFragment(fragmentManager: FragmentManager) =
            fragmentManager.beginTransaction()
                .replace(R.id.container, BottomNavigationFragment.newInstance())
                .commit()
    }
}