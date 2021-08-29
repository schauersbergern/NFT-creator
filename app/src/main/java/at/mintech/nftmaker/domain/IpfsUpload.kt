package at.mintech.nftmaker.domain

import at.mintech.nftmaker.data.IpfsManager
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class IpfsUpload(
    private val ipfsManager: IpfsManager
) : AsyncUseCase<String, ByteArray>() {
    override suspend fun run(params: ByteArray) = Result.runCatching {
        withContext(Dispatchers.Main) {
            val hash = ipfsManager.addFile("Testfile", params)
            hash
        }
    }
}