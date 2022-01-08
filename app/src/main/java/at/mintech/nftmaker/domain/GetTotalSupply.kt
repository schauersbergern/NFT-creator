package at.mintech.nftmaker.domain

import at.mintech.nftmaker.contracts.MyToken
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.util.concurrent.Future

class GetTotalSupply(
    private val token: MyToken
) : AsyncUseCase<BigInteger, Unit>() {
    override suspend fun run(params: Unit) = Result.runCatching {
        withContext(Dispatchers.IO) {
            val totalSupply: Future<BigInteger> = token.totalSupply().sendAsync()
            val convertToBigInt: BigInteger = totalSupply.get()
            convertToBigInt
        }
    }
}