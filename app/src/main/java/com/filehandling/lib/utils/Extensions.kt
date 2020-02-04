package com.filehandling.lib.utils

/**
 * This file is created by Lalit N. Hajare on 2/4/2020.
 */

fun Any?.getClassName(): String {
    return this!!::class.java.simpleName
}