package com.pivoto.codeassessment.repository

import com.pivoto.codeassessment.models.Photo

interface PhotosRepository {

        suspend fun getPhotos(page: Int = 1): Result<List<Photo>>
}
