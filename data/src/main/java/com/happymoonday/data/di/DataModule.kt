package com.happymoonday.data.di

import com.happymoonday.data.repository.impl.ProductRepositoryImpl
import com.happymoonday.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Create Date: 2024. 11. 24.
 *
 *
 * data 모듈 DI 설정
 * @author LeeDongHun
 *
 *
**/
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    /**
     * product repository 의존성 주입
     */
    @Binds
    @Singleton
    abstract fun provideDataRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository
}
