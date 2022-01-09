package at.mintech.nftmaker.ui.scan

import androidx.lifecycle.ViewModel
import at.mintech.nftmaker.domain.PersistUserAddress
import at.mintech.nftmaker.domain.TransformToEthereumAddress
import at.mintech.nftmaker.helper.config.INVALID_ADDRESS
import at.mintech.nftmaker.helper.config.component1
import at.mintech.nftmaker.helper.config.component2
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

data class ScanViewModelState(
    val ethereumAddress : String = ""
)

sealed class ScanViewModelSideEffects {
    data class ShowError(val error: String) : ScanViewModelSideEffects()
}

class ScanViewModel(
    private val transformToEthereumAddress: TransformToEthereumAddress,
    private val persistUserAddress: PersistUserAddress
) : ViewModel(), ContainerHost<ScanViewModelState, Any> {

    fun setEthereumAddress(address: String) = intent {

        val (transformed, error) = transformToEthereumAddress(address)

        if (error != null && error.message == INVALID_ADDRESS) {
            postSideEffect(ScanViewModelSideEffects.ShowError(error.message!!))
        } else if (transformed != null) {
            persistUserAddress(transformed).onSuccess {
                reduce {
                    state.copy(ethereumAddress = transformed)
                }
            }
        }
    }

    override val container = container<ScanViewModelState, Any>(ScanViewModelState())

}