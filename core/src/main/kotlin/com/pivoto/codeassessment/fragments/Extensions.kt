package com.pivoto.codeassessment.fragments

import android.view.LayoutInflater
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Helpful extension to inflate a [ViewBinding] from withing a fragment
 */
@MainThread
inline fun <reified VB : ViewBinding> Fragment.viewBinding(): VB {
    val method = VB::class.java.getMethod("inflate", LayoutInflater::class.java)
    return method.invoke(null, layoutInflater) as VB
}
