package at.mintech.nftmaker.utils

import android.content.SharedPreferences
import io.mockk.every
import io.mockk.justRun

internal fun setupMockkSharedPreferences(
    sharedPreferences: SharedPreferences,
    editor: SharedPreferences.Editor
) {
    every { sharedPreferences.getString(any(), any()) } returns null
    every { sharedPreferences.getLong(any(), any()) } returns 0L
    every { sharedPreferences.edit() } returns editor
    every { editor.putString(any(), any()) } returns editor
    justRun { editor.apply() }
    justRun { editor.commit() }
    every { editor.remove(any()) } returns editor
}