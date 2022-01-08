package at.mintech.nftmaker.domain

import at.mintech.nftmaker.contracts.NFTContract
import at.mintech.nftmaker.domain.entities.MintParams
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.protocol.core.methods.response.TransactionReceipt

internal class MintNft(
    private val nftContract: NFTContract
) : AsyncUseCase<TransactionReceipt, MintParams>() {
    override suspend fun run(params: MintParams) = Result.runCatching {
        withContext(Dispatchers.IO) {
            //val mint = nftContract.mint(params.address, params.tokenId, params.nftUrl).sendAsync()
            //mint.get()
            TransactionReceipt()
        }
    }
}