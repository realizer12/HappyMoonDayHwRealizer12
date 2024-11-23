package com.happymoonday.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.happymoonday.local.room.dao.FavoriteCollectionDao

/**
 * Create Date: 2024. 11. 24.
 *
 *
 * 헤이문 과제앱 로컬 데이터베이스 추상 클래스
 *
 * @property getFavoriteCollectionDao 즐겨찾기 컬렉션 Dao
 *
 * @author LeeDongHun
 *
 **/
@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
internal abstract class HayMoonLocalDataBase:RoomDatabase() {
    abstract fun getFavoriteCollectionDao(): FavoriteCollectionDao
}
