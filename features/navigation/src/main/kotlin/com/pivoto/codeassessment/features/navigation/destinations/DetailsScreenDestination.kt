package com.pivoto.codeassessment.features.navigation.destinations

import android.os.Bundle
import com.pivoto.codeassessment.features.navigation.R
import com.pivoto.codeassessment.models.Photo
import com.pivoto.codeassessment.navigation.Destination

data class DetailsScreenDestination(private val photo: Photo) : Destination {

    override val destinationId: Int
        get() = R.id.details_screen_destination_id

    override val resultListenerKey: String
        get() = "details_screen_result"

    override fun getArgsBundle(): Bundle = Bundle().apply { putParcelable(PHOTO_ID_PARAM, photo) }

    companion object {
        const val PHOTO_ID_PARAM = "photo_id"
    }
}
