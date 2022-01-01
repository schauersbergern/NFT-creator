package at.mintech.nftmaker.helper.config

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap

fun Uri.getFileType(ctx : Context) : String? {
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(ctx.contentResolver.getType(this))
}