package com.happymoonday.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.happymoonday.local.model.SemaPsgudInfoKorInfoRowLocalDataModel

/**
 * Create Date: 2024. 11. 24.
 *
 * 소장품 즐겨찾기 용 dao interface
 *
 * @author LeeDongHun
 *
 *
**/
@Dao
interface FavoriteCollectionDao {

    /**
     * 소장품 즐겨찾기 설정
    **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setBookMarkArtProduct(semaPsgudInfoKorInfoRowLocalDataModel: SemaPsgudInfoKorInfoRowLocalDataModel)

    /**
     * 소장품 즐겨찾기 해제
     *
     * @param id 소장품 id - 저장시 만들어지는 @PrimaryKey
    **/
    @Query("DELETE FROM bookMarkedArtProductTable WHERE id = :id")
    suspend fun deleteBookMarkArtProduct(id: Long)

    /**
     * 소장품 즐겨찾기 리스트 조회
    **/
    @Query("SELECT * FROM bookMarkedArtProductTable")
    suspend fun getBookMarkArtProductList(): List<SemaPsgudInfoKorInfoRowLocalDataModel>

}
