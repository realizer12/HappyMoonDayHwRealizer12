package com.happymoonday.local.di

import android.content.Context
import androidx.room.Room
import com.happymoonday.local.room.dao.FavoriteCollectionDao
import com.happymoonday.local.room.database.HayMoonLocalDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Create Date: 2024. 11. 24.
 *
 * local database 관련 외부 의존성 주입을 위한 Hilt 모듈
 *
 * @author LeeDongHun
 *
 *
**/
@Module
@InstallIn(SingletonComponent::class)
internal object LocalDataBaseSettingModule {

    //room 주입용
    @Provides
    @Singleton
    fun provideRoomDataBase(
        @ApplicationContext context: Context
    ): HayMoonLocalDataBase {
        return Room.databaseBuilder(
            context,
            HayMoonLocalDataBase::class.java,
            "hay_moon_local-database.db"
        ).fallbackToDestructiveMigration()
          .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteCollectionDao(
        hayMoonLocalDataBase: HayMoonLocalDataBase
    ) :FavoriteCollectionDao = hayMoonLocalDataBase.getFavoriteCollectionDao()
}
