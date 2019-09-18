package com.malenalbc.stormcomics.data.core.extension

import java.security.MessageDigest

fun String?.removeWhitespaces() = this?.replace("\\s".toRegex(), "")

fun String.md5(): String {
    return hashString("md5", this)
}

private fun hashString(type: String, input: String): String {
    val HEX_CHARS = "0123456789ABCDEF"
    val bytes = MessageDigest
        .getInstance(type)
        .digest(input.toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}
