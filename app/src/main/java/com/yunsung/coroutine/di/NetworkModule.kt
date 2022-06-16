package com.yunsung.coroutine.di

import com.yunsung.coroutine.data.artdata.remote.artgallery.ArtGalleryService
import com.yunsung.coroutine.data.naverdata.remote.naver.NaverSearchService
import com.yunsung.coroutine.util.SelfSigningHelper
import com.yunsung.coroutine.util.Utils.BASE_URL_ARTGALLERY
import com.yunsung.coroutine.util.Utils.BASE_URL_NAVERDEARCH
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(selfSigningHelper: SelfSigningHelper): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
//            .sslSocketFactory(
//                selfSigningHelper.sslContext.socketFactory,
//                selfSigningHelper.tmf.trustManagers[0] as X509TrustManager
//            )
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_NAVERDEARCH)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


    @Provides
    @Singleton
    fun provideArtGalleryApiService(retrofit: Retrofit): ArtGalleryService {
        return retrofit.create(ArtGalleryService::class.java)
    }

    @Provides
    @Singleton
    fun provideNaverApiService(retrofit: Retrofit): NaverSearchService {
        return retrofit.create(NaverSearchService::class.java)
    }


    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}