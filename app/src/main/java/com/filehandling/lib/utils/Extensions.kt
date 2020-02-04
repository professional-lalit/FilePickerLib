package com.filehandling.lib.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

/**
 * This file is created by Lalit N. Hajare on 2/4/2020.
 */

fun Any?.getClassName(): String {
    return this!!::class.java.simpleName
}

fun <T : AppCompatActivity> AppCompatActivity.beginActivity(activityClass: Class<T>) {
    startActivity(Intent(this, activityClass))
}

fun <T : AppCompatActivity> AppCompatActivity.beginActivityForResult(
    activityClass: Class<T>,
    reqCode: Int
) {
    startActivityForResult(Intent(this, activityClass), reqCode)
}