package at.mintech.nftmaker.ui.token

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.mintech.nftmaker.domain.LoadTokenData
import at.mintech.nftmaker.domain.TransferToken
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.math.BigInteger

data class TokenState(
    val totalSupply: BigInteger = BigInteger.valueOf(0),
    val creatorAccountBalance: BigInteger = BigInteger.valueOf(0),
    val receiverAccountBalance: BigInteger = BigInteger.valueOf(0),
)

sealed class TokenSideEffects {
    object ContentLoading : TokenSideEffects()
    object ContentLoaded : TokenSideEffects()
}

internal class TokenViewModel(
    private val loadTokenData: LoadTokenData,
    private val transfer: TransferToken,
) : ViewModel(), ContainerHost<TokenState, TokenSideEffects> {

    override val container = container<TokenState, TokenSideEffects>(TokenState())
    private var dataJob: Job? = null

    fun init() = intent {
        loadData()
    }

    fun obtainTokens(amount: Int) = intent {
        postSideEffect(TokenSideEffects.ContentLoading)
        transfer(amount).onSuccess {
            if (it) {
                loadData()
            }
        }
    }

    private fun loadData() = intent {
        dataJob?.cancel()
        dataJob = viewModelScope.launch {
            postSideEffect(TokenSideEffects.ContentLoading)
            loadTokenData(Unit).onSuccess {
                reduce {
                    state.copy(it.totalSupply, it.creatorAccountBalance, it.receiverAccountBalance)
                }
                postSideEffect(TokenSideEffects.ContentLoaded)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        dataJob?.cancel()
    }
}