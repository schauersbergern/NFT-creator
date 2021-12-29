package at.mintech.nftmaker.ui.displayNfts

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container


object DisplayNftsState

sealed class DisplayNftsSideEffects

internal class DisplayNftsViewModel : ViewModel(), ContainerHost<DisplayNftsState, DisplayNftsSideEffects> {

    override val container =
        container<DisplayNftsState, DisplayNftsSideEffects>(DisplayNftsState)

}