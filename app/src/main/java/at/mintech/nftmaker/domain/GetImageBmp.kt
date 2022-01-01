package at.mintech.nftmaker.domain

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import at.mintech.nftmaker.helper.usecase.AsyncUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetImageBmp : AsyncUseCase<Bitmap, String>() {
    override suspend fun run(params: String) = Result.runCatching {
        withContext(Dispatchers.IO) {
            BitmapFactory.decodeStream(java.net.URL(params).openStream())
        }
    }
}