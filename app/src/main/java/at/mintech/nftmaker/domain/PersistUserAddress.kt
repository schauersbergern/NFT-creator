package at.mintech.nftmaker.domain

import android.content.SharedPreferences
import at.mintech.nftmaker.helper.config.USER_ADDRESS_KEY
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersistUserAddress (private val sp : SharedPreferences) : AsyncUseCase<Unit, String>() {
    override suspend fun run(params: String): Result<Unit> = Result.runCatching {
        withContext(Dispatchers.IO) {
            sp.edit().putString(USER_ADDRESS_KEY, params).apply()
        }
    }
}