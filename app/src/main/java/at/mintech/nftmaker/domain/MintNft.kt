package at.mintech.nftmaker.domain

import at.mintech.nftmaker.contracts.NFToken
import at.mintech.nftmaker.domain.entities.MintParams
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MintNft(
    private val nfToken: NFToken
) : AsyncUseCase<Unit, MintParams>() {
    override suspend fun run(params: MintParams) = Result.runCatching {
        withContext(Dispatchers.Main) {
            val mint = nfToken.mint(params.address, params.ipfsHash.toByteArray()).sendAsync()
            val result = mint.get()
        }
    }
}