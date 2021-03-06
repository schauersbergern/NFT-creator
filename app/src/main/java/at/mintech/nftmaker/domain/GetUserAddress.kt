package at.mintech.nftmaker.domain

import android.content.SharedPreferences
import at.mintech.nftmaker.helper.config.USER_ADDRESS_KEY
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserAddress(private val sp : SharedPreferences) : AsyncUseCase<String, Unit>() {
    override suspend fun run(params: Unit): Result<String> = Result.runCatching {
        withContext(Dispatchers.IO) {
            sp.getString(USER_ADDRESS_KEY , null) ?: ""
        }
    }
}