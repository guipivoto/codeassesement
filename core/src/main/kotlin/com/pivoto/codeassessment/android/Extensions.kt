package com.pivoto.codeassessment.android

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

/**
 * Helpful method to get a parcelable from a Bundle which properly handle the deprecation of some
 * methods on SDK 33
 */
inline fun <reified T: Parcelable> Bundle.parcelable(key: String): T?  {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val clazz = T::class.java
        this.getParcelable(key, clazz)
    } else {
        @Suppress("DEPRECATION")
        this.getParcelable(key) as T?
    }
}