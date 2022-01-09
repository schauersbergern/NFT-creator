package at.mintech.nftmaker.domain

import at.mintech.nftmaker.domain.entities.TokenData
import at.mintech.nftmaker.helper.config.ACCOUNT_RECEIVER
import at.mintech.nftmaker.helper.config.ACCOUNT_SENDER
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadTokenData (
    private val getBalance: GetBalance,
    private val getTotalSupply: GetTotalSupply,
    private val getUserAddress: GetUserAddress
) : AsyncUseCase<TokenData, Unit>() {
    override suspend fun run(params: Unit) = Result.runCatching {
        withContext(Dispatchers.IO) {
            val totalSupply = getTotalSupply(Unit).getOrThrow()
            val creatorAccountBalance = getBalance(ACCOUNT_SENDER).getOrThrow()
            val accountReceiver = getUserAddress(Unit).getOrThrow()
            val receiverAccountBalance = getBalance(accountReceiver).getOrThrow()
            TokenData(totalSupply, creatorAccountBalance, receiverAccountBalance)
        }
    }
}