package com.erolc.applinkexample

import android.content.*
import android.net.Uri
import java.io.File

val Context.clipboard get() = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

fun <T> Context.copy(data: T, type: ClipType = TEXT) {
    val clip = when (type) {
        TEXT -> ClipData.newPlainText("label", data.toString())
        HTML -> ClipData.newHtmlText("label", data.toString(), data.toString())
        else -> {
            val uri = if (data is File) {
                Uri.fromFile(data)
            } else {
                Uri.parse(data.toString())
            }
            ClipData.newRawUri("label", uri)
        }
    }
    clipboard.primaryClip = clip
}

fun ClipboardManager.paste(type: ClipType = TEXT): String {
    if (hasPrimaryClip()) {
        val primaryClip = primaryClip.getItemAt(0)
        return when (type) {
            TEXT -> primaryClip.text.toString()
            HTML -> primaryClip.htmlText
            else -> primaryClip.uri.toString()
        }
    }
    return ""
}

fun ClipboardManager.copyUri(resolver: ContentResolver,
                             uri:Uri){
    primaryClip = ClipData.newUri(resolver, "label", uri)
}

fun ClipboardManager.pasteUri(): Uri {
    if (hasPrimaryClip()) {
        return primaryClip.getItemAt(0).uri
    }
    return Uri.parse("")
}

fun ClipboardManager.copyIntent(intent: Intent) {
    primaryClip = ClipData.newIntent("label", intent)
}

fun ClipboardManager.pasteIntent(): Intent? {
    if (hasPrimaryClip()) {
        primaryClip.getItemAt(0).intent
    }
    return null
}

sealed class ClipType

object TEXT : ClipType()
object HTML : ClipType()
object URI : ClipType()

