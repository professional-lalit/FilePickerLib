package com.filehandling.lib.models

import android.net.Uri

/**
 * This file is created by Lalit N. Hajare on 2/4/2020.
 */

data class LibFile(
    val uri: Uri,
    var isSelected: Boolean,
    val name: String,
    val duration: Int,
    val size: Int
) {
    override fun equals(other: Any?): Boolean {
        return this.uri == (other as LibFile).uri
    }

    override fun hashCode(): Int {
        return uri.hashCode()
    }
}