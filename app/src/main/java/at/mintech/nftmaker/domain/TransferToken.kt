package at.mintech.nftmaker.domain

import at.mintech.nftmaker.contracts.MyToken
import at.mintech.nftmaker.helper.config.ACCOUNT_RECEIVER
import at.mintech.nftmaker.helper.config.ACCOUNT_SENDER
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import java8.util.concurrent.CompletableFuture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.protocol.core.methods.response.TransactionReceipt
import java.math.BigInteger

class TransferToken(
    private val token: MyToken
) : AsyncUseCase<Boolean, Int>() {
    override suspend fun run(params: Int) = Result.runCatching {
        withContext(Dispatchers.Main) {

            val transfer: CompletableFuture<TransactionReceipt> =
                token.transfer(ACCOUNT_RECEIVER, BigInteger.valueOf(params.toLong())).sendAsync()

            val convert: TransactionReceipt = transfer.get()
            convert.isStatusOK
        }
    }
}