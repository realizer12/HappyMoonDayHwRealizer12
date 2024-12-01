package com.happymoonday.local.datasource.impl

import com.happymoonday.data.datasource.ProductLocalDataSource
import com.happymoonday.data.model.SemaPsgudInfoKorInfoRowDataModel
import com.happymoonday.local.model.SemaPsgudInfoKorInfoRowLocalDataModel.Companion.FromData
import com.happymoonday.local.model.SemaPsgudInfoKorInfoRowLocalDataModel.Companion.toData
import com.happymoonday.local.room.dao.FavoriteCollectionDao
import javax.inject.Inject

/**
 * Create Date: 2024. 12. 1.
 *
 * 상품 로컬 데이터 소스 구현체
 *
 * @author LeeDongHun
 *
 *
**/
class ProductLocalDataSourceImpl @Inject constructor(
    private val favoriteCollectionDao: FavoriteCollectionDao,
) : ProductLocalDataSource {
    override suspend fun setBookMarkArtProduct(semaPsgudInfoKorInfoRowDataModel: SemaPsgudInfoKorInfoRowDataModel): Result<Unit> =
        runCatching {
            favoriteCollectionDao.setBookMarkArtProduct(
                semaPsgudInfoKorInfoRowDataModel.FromData()
            )
        }

    override suspend fun removeBookMarkArtProduct(productId: Long): Result<Unit> = runCatching {
        favoriteCollectionDao.deleteBookMarkArtProduct(productId)
    }

    override suspend fun getBookMarkArtProductList(): Result<List<SemaPsgudInfoKorInfoRowDataModel>> =
        runCatching {
            favoriteCollectionDao.getBookMarkArtProductList().map { it.toData() }
        }
}
