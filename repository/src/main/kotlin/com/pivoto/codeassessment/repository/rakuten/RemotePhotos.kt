package com.pivoto.codeassessment.repository.rakuten

import com.google.gson.annotations.SerializedName

data class RemotePhotos(
    @SerializedName("photos")
    val page: RemotePhotosPage
)