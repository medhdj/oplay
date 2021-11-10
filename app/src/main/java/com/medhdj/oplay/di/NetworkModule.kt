package com.medhdj.oplay.di

import com.medhdj.data.common.OcsApi
import com.medhdj.oplay.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OcsApi.BASE_URL)
            .client(createOkhttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOCSApi(retrofit: Retrofit): OcsApi =
        retrofit.create(OcsApi::class.java)

    private fun createOkhttp(): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.HEADERS
                })
            }

            build()
        }
    }
}
