package at.mintech.nftmaker.ui.createNft

import androidx.lifecycle.ViewModel
import at.mintech.nftmaker.data.IpfsManager
import at.mintech.nftmaker.domain.GetBalance
import at.mintech.nftmaker.domain.GetTotalSupply
import at.mintech.nftmaker.domain.GetUserAddress
import at.mintech.nftmaker.domain.MintNft
import at.mintech.nftmaker.domain.TransferToken
import at.mintech.nftmaker.domain.entities.MintParams
import at.mintech.nftmaker.helper.config.ACCOUNT_RECEIVER
import at.mintech.nftmaker.helper.config.ACCOUNT_SENDER
import at.mintech.nftmaker.helper.config.IPFS_URL
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.syntax.simple.postSideEffect
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
    object ContentLoaded : MainViewModelSideEffects()
    data class ShowError(val error: String) : MainViewModelSideEffects()
}

internal class CreateNftViewModel(
    private val ipfsManager: IpfsManager,
    private val getTotalSupply: GetTotalSupply,
    private val getBalance: GetBalance,
    private val transfer: TransferToken,
    private val getUserAddress: GetUserAddress,
    private val mintNft: MintNft
) : ViewModel(), ContainerHost<MainViewModelState, MainViewModelSideEffects> {

    override val container =
        container<MainViewModelState, MainViewModelSideEffects>(MainViewModelState())

    fun uploadData(data: ByteArray) = intent {
        val ipfsUrl = IPFS_URL + ipfsManager.addFile(FILENAME, data)
        reduce {
            state.copy(nftUrl = ipfsUrl)
        }
    }

    //TODO: helper, remove
    fun setIpfsUrl(url: String) = intent {
        postSideEffect(MainViewModelSideEffects.Loading)

        mintNft(
            MintParams(
                getUserAddress(Unit).getOrDefault(DEFAULT_ADDRESS),
                BigInteger("1"),
                url
            )
        ).fold(
            {
                reduce {
                    state.copy(nftUrl = url)
                }
            },
            { postSideEffect(MainViewModelSideEffects.ShowError(it.message ?: DEFAULT_ERROR_MSG)) }
        )

        postSideEffect(MainViewModelSideEffects.ContentLoaded)
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

    companion object {
        const val FILENAME = "Testfile"
        const val DEFAULT_ADDRESS = ""
        const val DEFAULT_ERROR_MSG = "An unknown error happened"
    }

}