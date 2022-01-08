package at.mintech.nftmaker.domain

import android.content.SharedPreferences
import at.mintech.nftmaker.domain.entities.Nft
import at.mintech.nftmaker.helper.config.PERSISTED_NFT_KEY
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GetPersistedNfts(private val sp : SharedPreferences) : AsyncUseCase<List<Nft>, Unit>() {
    override suspend fun run(params: Unit): Result<List<Nft>> = Result.runCatching {
        withContext(Dispatchers.IO) {
            val nftString = sp.getString(PERSISTED_NFT_KEY,"[]")
            nftString?.let { Json.decodeFromString<List<Nft>>(it) } as MutableList<Nft>
        }
    }
}