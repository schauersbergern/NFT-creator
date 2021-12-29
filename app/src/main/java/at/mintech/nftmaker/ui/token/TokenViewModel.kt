package at.mintech.nftmaker.ui.token

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

object TokenState

sealed class TokenSideEffects

internal class TokenViewModel : ViewModel(),
    ContainerHost<TokenState, TokenSideEffects> {

    override val container = container<TokenState, TokenSideEffects>(TokenState)

}