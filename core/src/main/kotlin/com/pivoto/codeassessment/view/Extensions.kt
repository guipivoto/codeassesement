package com.pivoto.codeassessment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.viewbinding.ViewBinding

/**
 * Helpful extension to inflate a [ViewBinding] from withing a view
 */
@MainThread
inline fun <reified VB : ViewBinding> View.inflateViewBinding(): VB {
    val method = VB::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return method.invoke(null, LayoutInflater.from(context), this, false) as VB
}
