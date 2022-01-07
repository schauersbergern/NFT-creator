package at.mintech.nftmaker.domain

import android.content.SharedPreferences
import at.mintech.nftmaker.domain.entities.Nft
import at.mintech.nftmaker.helper.config.PERSISTED_NFT_KEY
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.*

class PersistNft(private val sp : SharedPreferences) : AsyncUseCase<Unit, Nft>() {
    override suspend fun run(params: Nft): Result<Unit> = Result.runCatching {
        withContext(Dispatchers.Main) {

            val nftString = sp.getString(PERSISTED_NFT_KEY,"[]")
            val obj = nftString?.let { Json.decodeFromString<List<Nft>>(it) } as MutableList<Nft>
            obj.add(params)

            sp.edit().putString(PERSISTED_NFT_KEY, Json.encodeToString(obj)).apply()
        }
    }
}