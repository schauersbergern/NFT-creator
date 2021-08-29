package at.mintech.nftmaker.domain

import at.mintech.nftmaker.contracts.MyToken
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.util.concurrent.Future

internal class GetBalance (
    private val token: MyToken
) : AsyncUseCase<BigInteger, String>() {
    override suspend fun run(params: String) = Result.runCatching {
        withContext(Dispatchers.Main) {
            val balanceOf: Future<BigInteger> = token.balanceOf(params).sendAsync()
            val convertedToBigInt: BigInteger = balanceOf.get()
            convertedToBigInt
        }
    }
}