package at.mintech.nftmaker.domain

import at.mintech.nftmaker.contracts.NFTContract
import at.mintech.nftmaker.domain.entities.MintParams
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MintNft(
    private val nftContract: NFTContract
) : AsyncUseCase<Unit, MintParams>() {
    override suspend fun run(params: MintParams) = Result.runCatching {
        withContext(Dispatchers.Main) {
            val mint = nftContract.mint(params.address, params.tokenId.toBigInteger(), params.nftUrl).sendAsync()
            val result = mint.get()
        }
    }
}