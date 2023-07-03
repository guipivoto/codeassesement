package com.pivoto.codeassessment.repository.rakuten

import com.google.gson.annotations.SerializedName

data class RemotePhotosPage(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("perpage")
    val itemsPerPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("photo")
    val photos: List<RemotePhoto>
)