package com.filehandling.lib.utils

import android.content.Context
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File

/**
 * This file is created by Lalit N. Hajare on 1/30/2020.
 */
class FileHandler {

    fun handlePath(context: Context, file: File): android.net.Uri {
        var path: android.net.Uri

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            path = android.net.Uri.fromFile(file)
        } else {
            path = FileProvider.getUriForFile(context, context.packageName, file)
        }
        return path
    }

}