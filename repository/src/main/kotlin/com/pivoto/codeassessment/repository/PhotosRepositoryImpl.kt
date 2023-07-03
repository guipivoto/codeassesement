package com.pivoto.codeassessment.repository

import com.pivoto.codeassessment.kotlin.onBackground
import com.pivoto.codeassessment.models.Photo
import com.pivoto.codeassessment.repository.rakuten.RakutenAPI
import retrofit2.HttpException

class PhotosRepositoryImpl(private val rakutenApi: RakutenAPI) : PhotosRepository {

    override suspend fun getPhotos(page: Int): Result<List<Photo>> = onBackground {
        var result: List<Photo>? = null
        var exception: Exception? = null
        try {
            val response = rakutenApi.getPhotos()
            if (response.isSuccessful) {
                result = response.body()?.page?.photos?.map {
                    Photo(
                        id = it.id,
                        owner = it.owner,
                        secret = it.secret,
                        server = it.server,
                        farm = it.farm,
                        title = it.title,
                        isFamily = it.isFamily == 1,
                        isFriend = it.isFriend == 1,
                        isPublic = it.isPublic == 1
                    )
                }
            } else {
                exception = RuntimeException(
                    "Failed to retrieve photos. Code: ${response.code()} Error: ${response.errorBody()}"
                )
            }
        } catch (ex: HttpException) {
            exception = ex
        }

        return@onBackground if (exception != null) {
            Result.failure(exception)
        } else {
            Result.success(result ?: emptyList())
        }
    }
}
