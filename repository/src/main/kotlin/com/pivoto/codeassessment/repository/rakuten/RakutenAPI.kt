package com.pivoto.codeassessment.repository.rakuten

import retrofit2.Response
import retrofit2.http.GET

interface RakutenAPI {

    @GET("rakuten-rewards/photos.json")
    suspend fun getPhotos(): Response<RemotePhotos>
}
