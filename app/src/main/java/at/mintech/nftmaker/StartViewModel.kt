package at.mintech.nftmaker

import androidx.lifecycle.ViewModel
import at.mintech.nftmaker.domain.GetUserAddress
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import at.mintech.nftmaker.StartSideEffect.StartScan
import at.mintech.nftmaker.StartSideEffect.StartNFT

object StartState

sealed class StartSideEffect {
    object StartScan : StartSideEffect()
    object StartNFT : StartSideEffect()
}

class StartViewModel(
    private val getUserAddress: GetUserAddress
) : ViewModel(), ContainerHost<StartState, StartSideEffect> {
    override val container = container<StartState, StartSideEffect>(StartState)

    fun getAddress() = intent {
        getUserAddress(Unit).onSuccess {
            if (it == "") {
                postSideEffect(StartScan)
            } else {
                postSideEffect(StartNFT)
            }
        }
    }
}