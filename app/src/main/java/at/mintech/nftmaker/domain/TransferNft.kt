package at.mintech.nftmaker.domain

import at.mintech.nftmaker.contracts.NFToken
import at.mintech.nftmaker.domain.entities.TransferParams
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class TransferNft(
    private val nfToken: NFToken
) : AsyncUseCase<Unit, TransferParams>() {
    override suspend fun run(params: TransferParams) = Result.runCatching {
        withContext(Dispatchers.IO) {
            val transfer = nfToken.transfer(params.address, params.tokenId).sendAsync()
            val result = transfer.get()
        }
    }
}