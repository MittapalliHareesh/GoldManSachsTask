package com.goldmanscachs.task.api

import com.goldmanscachs.task.MainApplication
import com.goldmanscachs.task.repository.AstronomyPictureRepository
import com.goldmanscachs.task.util.AppConstants
import com.goldmanscachs.task.util.InternetConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL = AppConstants.baseUrl
    private const val cacheSize = (5 * 1024 * 1024).toLong() //It specifies a cache of 5MB
    private const val days = 60 * 60 * 24 * 1


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .cache(Cache(MainApplication.instance.cacheDir, cacheSize))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (InternetConnection.checkNetworkConnection(MainApplication.instance)) {
                    /**
                     *  If there is Internet, get the cache that was stored 5 seconds ago.
                     *  If the cache is older than 5 seconds, then discard it,
                     *  and indicate an error in fetching the response.
                     *  The 'max-age' attribute is responsible for this behavior.
                     */
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, max-age=" + 5
                    ).build()
                } else {
                    /**
                     *  If there is no Internet, get the cache that was stored 7 days ago.
                     *  If the cache is older than 7 days, then discard it,
                     *  and indicate an error in fetching the response.
                     *  The 'max-stale' attribute is responsible for this behavior.
                     *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                     */
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$days"
                    ).build()
                }
                // Add the modified request to the chain.
                chain.proceed(request)
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: ApiService) = AstronomyPictureRepository(apiService)
}

