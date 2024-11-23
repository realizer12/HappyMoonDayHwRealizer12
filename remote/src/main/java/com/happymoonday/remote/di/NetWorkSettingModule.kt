package com.happymoonday.remote.di

import com.happymoonday.remote.BuildConfig
import com.happymoonday.remote.retrofit.ApiService
import com.happymoonday.remote.util.ServerHost
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

/**
 * Create Date: 2024. 11. 24.
 *
 * network 관련 외부 의존성 주입을 위한 Hilt 모듈
 *
 * @author LeeDongHun
 *
**/
@Module
@InstallIn(SingletonComponent::class)
internal object NetWorkSettingModule {

    private const val RETROFIT_WRITE_TIME_OUT = 15L
    private const val RETROFIT_READ_TIME_OUT = 15L
    private const val RETROFIT_CONNECT_TIME_OUT =20L

    /**
     * api service 인스턴스 생성
     */
    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService =
        retrofit.create(ApiService::class.java)
    
    /**
     * retrofit 인스턴스 생성
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ServerHost.SEOUL_API_HOST)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * okhttp client 인스턴스 생성
     */
    @Provides
    @Singleton
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(RETROFIT_CONNECT_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(RETROFIT_READ_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(RETROFIT_WRITE_TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    /**
     * http log interceptor 인스턴스 생성
    **/
    @Provides
    @Singleton
    fun provideHttpLogInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {//디버그가 아니면  로그가 안보이게 해준다.
                HttpLoggingInterceptor.Level.NONE
            }
        )

}
