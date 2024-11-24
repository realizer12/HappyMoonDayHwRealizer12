package com.happymoonday.remote.di

import com.happymoonday.data.datasource.ProductRemoteDataSource
import com.happymoonday.remote.datasource.impl.ProductRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Create Date: 2024. 11. 24.
 *
 * remote data source 의존성 주입을 위한 Hilt 모듈
 *
 * @author LeeDongHun
 *
 **/
@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteModule {

    /**
     * product remote data source 의존성 주입
     */
    @Binds
    @Singleton
    abstract fun provideProductRemoteDataSource(
        productRemoteDataSourceImpl: ProductRemoteDataSourceImpl
    ): ProductRemoteDataSource
}
