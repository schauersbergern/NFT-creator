package at.mintech.nftmaker.domain

import at.mintech.nftmaker.contracts.MyToken
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import java8.util.concurrent.CompletableFuture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.protocol.core.methods.response.TransactionReceipt
import java.math.BigInteger

class TransferToken(
    private val token: MyToken,
    private val getUserAddress: GetUserAddress
) : AsyncUseCase<Boolean, Int>() {
    override suspend fun run(params: Int) = Result.runCatching {
        withContext(Dispatchers.IO) {
            val accountReceiver = getUserAddress(Unit).getOrThrow()
            val transfer: CompletableFuture<TransactionReceipt> =
                token.transfer(accountReceiver, BigInteger.valueOf(params.toLong())).sendAsync()

            val convert: TransactionReceipt = transfer.get()
            convert.isStatusOK
        }
    }
}