package com.pivoto.codeassessment.repository.di

import com.pivoto.codeassessment.repository.PhotosRepository
import com.pivoto.codeassessment.repository.PhotosRepositoryImpl
import com.pivoto.codeassessment.repository.rakuten.RakutenAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryProvider {

    @Provides
    @Singleton
    fun provideUserRepository(): PhotosRepository {
        return PhotosRepositoryImpl(
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://yuriy.me")
                .client(OkHttpClient.Builder().build())
                .build()
                .create(RakutenAPI::class.java)
        )
    }
}