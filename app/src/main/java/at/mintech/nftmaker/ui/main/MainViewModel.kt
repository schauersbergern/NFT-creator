package at.mintech.nftmaker.ui.main

import androidx.lifecycle.ViewModel
import at.mintech.nftmaker.data.IpfsManager
import at.mintech.nftmaker.domain.GetBalance
import at.mintech.nftmaker.domain.GetTotalSupply
import at.mintech.nftmaker.domain.TransferToken
import at.mintech.nftmaker.helper.config.ACCOUNT_RECEIVER
import at.mintech.nftmaker.helper.config.ACCOUNT_SENDER
import at.mintech.nftmaker.helper.config.IPFS_URL
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.math.BigInteger

data class MainViewModelState(
    val totalSupply: BigInteger = BigInteger.valueOf(0),
    val creatorAccountBalance: BigInteger = BigInteger.valueOf(0),
    val receiverAccountBalance: BigInteger = BigInteger.valueOf(0),
    val nftUrl: String = ""
)

sealed class MainViewModelSideEffects {
    object Loading : MainViewModelSideEffects()
}

class MainViewModel(
    private val ipfsManager: IpfsManager,
    private val getTotalSupply: GetTotalSupply,
    private val getBalance: GetBalance,
    private val transfer: TransferToken
    ) : ViewModel(), ContainerHost<MainViewModelState, MainViewModelSideEffects> {

    override val container = container<MainViewModelState, MainViewModelSideEffects>(MainViewModelState())

    fun uploadData(data: ByteArray) = intent {
        val ipfsUrl = IPFS_URL + ipfsManager.addFile("TestFile", data)
        reduce {
            state.copy(nftUrl = ipfsUrl)
        }
    }

    fun getTotalSupply() = intent {
        getTotalSupply(Unit).onSuccess {
            reduce {
                state.copy(totalSupply = it)
            }
        }
    }

    fun getCreatorBalance() = intent {
        getBalance(ACCOUNT_SENDER).onSuccess {
            reduce {
                state.copy(creatorAccountBalance = it)
            }
        }
    }

    fun getReceiverBalance() = intent {
        getBalance(ACCOUNT_RECEIVER).onSuccess {
            reduce {
                state.copy(receiverAccountBalance = it)
            }
        }
    }

    fun obtainTokens(amount: Int) = intent {
        transfer(amount).onSuccess {
            if (it) {
                getCreatorBalance()
                getReceiverBalance()
            }
        }
    }

}