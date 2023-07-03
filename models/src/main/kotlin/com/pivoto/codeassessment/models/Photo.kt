package com.pivoto.codeassessment.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Long,
    val title: String,
    val isFamily: Boolean,
    val isFriend: Boolean,
    val isPublic: Boolean
) : Parcelable
