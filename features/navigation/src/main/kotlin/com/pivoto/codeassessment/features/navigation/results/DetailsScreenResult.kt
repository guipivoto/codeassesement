package com.pivoto.codeassessment.features.navigation.results

import android.os.Bundle
import com.pivoto.codeassessment.navigation.NavigationResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsScreenResult(private val photoTitle: String) : NavigationResult {

    override fun getArgsBundle(): Bundle = Bundle().apply { putString(PHOTO_TITLE_PARAM, photoTitle) }

    companion object {
        const val PHOTO_TITLE_PARAM = "photo_title"
    }
}
