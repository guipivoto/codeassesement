package com.pivoto.codeassessment.repository

import com.pivoto.codeassessment.repository.rakuten.RakutenAPI
import com.pivoto.codeassessment.repository.rakuten.RemotePhoto
import com.pivoto.codeassessment.repository.rakuten.RemotePhotos
import com.pivoto.codeassessment.repository.rakuten.RemotePhotosPage
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class RakutenAPITests {

    private lateinit var rakutenApi: RakutenAPI
    private lateinit var repository: PhotosRepository

    @Before
    fun setup() {
        rakutenApi = mock()
        repository = PhotosRepositoryImpl(rakutenApi)
    }

    @Test
    fun testGetPhotosApiSuccess_01() = runBlocking {
        // given
        whenever(rakutenApi.getPhotos()).thenReturn(
            Response.success(RemotePhotos(RemotePhotosPage(0, 0, 0, 0, emptyList())))
        )

        // When
        val result = repository.getPhotos()

        // Then
        Assert.assertEquals(true, result.isSuccess)
        Assert.assertEquals(false, result.isFailure)
        Assert.assertNotNull(result.getOrNull())
        Assert.assertEquals(0, result.getOrNull()?.size)
    }

    @Test
    fun testGetPhotosApiSuccess_02() = runBlocking {
        // given
        whenever(rakutenApi.getPhotos()).thenReturn(
            Response.success(
                RemotePhotos(
                    RemotePhotosPage(
                        0, 0, 0, 0, listOf(
                            RemotePhoto(
                                "id", "owner", "secret", "server", 66L, "title", 0, 0, 0
                            )
                        )
                    )
                )
            )
        )

        // When
        val result = repository.getPhotos()

        // Then
        Assert.assertEquals(true, result.isSuccess)
        Assert.assertEquals(false, result.isFailure)
        Assert.assertNotNull(result.getOrNull())
        Assert.assertEquals(1, result.getOrNull()?.size)

        val photos = result.getOrThrow()
        Assert.assertEquals("id", photos[0].id)
        Assert.assertEquals("owner", photos[0].owner)
        Assert.assertEquals("secret", photos[0].secret)
        Assert.assertEquals("server", photos[0].server)
        Assert.assertEquals(66L, photos[0].farm)
        Assert.assertEquals("title", photos[0].title)
        Assert.assertEquals(false, photos[0].isPublic)
        Assert.assertEquals(false, photos[0].isFriend)
        Assert.assertEquals(false, photos[0].isFamily)
    }

    @Test
    fun testGetPhotosApiSuccess_03() = runBlocking {
        // given
        whenever(rakutenApi.getPhotos()).thenReturn(
            Response.success(
                RemotePhotos(
                    RemotePhotosPage(
                        0, 0, 0, 0, listOf(
                            RemotePhoto(
                                "id", "owner", "secret", "server", 66L, "title", 1, 1, 1
                            )
                        )
                    )
                )
            )
        )

        // When
        val result = repository.getPhotos()

        // Then
        val photos = result.getOrThrow()
        Assert.assertEquals(true, photos[0].isPublic)
        Assert.assertEquals(true, photos[0].isFriend)
        Assert.assertEquals(true, photos[0].isFamily)
    }

    @Test
    fun testGetPhotosApiFailure_01() = runBlocking {
        // given
        whenever(rakutenApi.getPhotos()).thenReturn(
            Response.error(500, ResponseBody.create(MediaType.parse("application/json"), ""))
        )

        // When
        val result = repository.getPhotos()

        // Then
        Assert.assertEquals(false, result.isSuccess)
        Assert.assertEquals(true, result.isFailure)
        Assert.assertNull(result.getOrNull())
    }

    @Test
    fun testGetPhotosApiFailure_02() = runBlocking {
        // given
        whenever(rakutenApi.getPhotos()).thenReturn(Response.success(null))

        // When
        val result = repository.getPhotos()

        // Then
        Assert.assertEquals(true, result.isSuccess)
        Assert.assertEquals(false, result.isFailure)
        Assert.assertNotNull(result.getOrNull())
        Assert.assertEquals(0, result.getOrNull()?.size)
    }
}
