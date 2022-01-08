package at.mintech.nftmaker.domain

import at.mintech.nftmaker.domain.entities.TokenData
import at.mintech.nftmaker.helper.config.ACCOUNT_RECEIVER
import at.mintech.nftmaker.helper.config.ACCOUNT_SENDER
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger

class LoadTokenData (
    private val getBalance: GetBalance,
    private val getTotalSupply: GetTotalSupply
) : AsyncUseCase<TokenData, Unit>() {
    override suspend fun run(params: Unit) = Result.runCatching {
        withContext(Dispatchers.IO) {
            val totalSupply = getTotalSupply(Unit).getOrThrow()
            val creatorAccountBalance = getBalance(ACCOUNT_SENDER).getOrThrow()
            val receiverAccountBalance = getBalance(ACCOUNT_RECEIVER).getOrThrow()
            TokenData(totalSupply, creatorAccountBalance, receiverAccountBalance)
        }
    }
}