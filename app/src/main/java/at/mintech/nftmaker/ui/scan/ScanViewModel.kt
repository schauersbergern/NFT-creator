package at.mintech.nftmaker.ui.scan

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

data class ScanViewModelState(
    val ethereumAddress : String = "Your Ethereum Address"
)

class ScanViewModel : ViewModel(), ContainerHost<ScanViewModelState, Any> {

    fun setEthereumAddress(address: String) = intent {
        reduce {
            state.copy(ethereumAddress = address)
        }
    }

    override val container = container<ScanViewModelState, Any>(ScanViewModelState())

}