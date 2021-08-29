package at.mintech.nftmaker.domain

import at.mintech.nftmaker.contracts.MyToken
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.util.concurrent.Future

internal class GetTotalSupply(
    private val token: MyToken
) : AsyncUseCase<BigInteger, Unit>() {
    override suspend fun run(params: Unit) = Result.runCatching {
        withContext(Dispatchers.Main) {
            val totalSupply: Future<BigInteger> = token.totalSupply().sendAsync()
            val convertToBigInt: BigInteger = totalSupply.get()
            convertToBigInt
        }
    }
}