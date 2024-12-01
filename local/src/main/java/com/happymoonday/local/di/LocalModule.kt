package com.happymoonday.local.di

import com.happymoonday.data.datasource.ProductLocalDataSource
import com.happymoonday.local.datasource.impl.ProductLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Create Date: 2024. 12. 1.
 *
 * @author LeeDongHun
 * 
 * 
**/
@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Singleton
    @Binds
    abstract fun provideProductLocalDataSource(
        productLocalDataSourceImpl: ProductLocalDataSourceImpl
    ): ProductLocalDataSource
}
