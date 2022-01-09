package at.mintech.nftmaker.helper.config

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import at.mintech.nftmaker.ui.createNft.CreateNftFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Uri.getFileType(ctx : Context) : String? {
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(ctx.contentResolver.getType(this))
}

operator fun <T> Result<T>.component1(): T? = getOrNull()
operator fun <T> Result<T>.component2(): Throwable? = exceptionOrNull()

inline fun <T> Result<T>.mapError(block: (Throwable) -> Throwable): Result<T> {
    return when {
        isSuccess -> this
        else -> try {
            Result.failure(block(exceptionOrNull()!!))
        } catch (error: Throwable) {
            Result.failure(error)
        }
    }
}

fun Throwable.toException() = if (this is Exception) this else Exception(message, this)

fun Context.showErrorDialog(error: String) = MaterialAlertDialogBuilder(this)
    .setMessage(error)
    .setNegativeButton(CreateNftFragment.ERROR_BTN_TEXT, null)
    .show()