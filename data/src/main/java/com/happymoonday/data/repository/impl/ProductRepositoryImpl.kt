package com.happymoonday.data.repository.impl

import com.happymoonday.data.datasource.ProductLocalDataSource
import com.happymoonday.data.datasource.ProductRemoteDataSource
import com.happymoonday.data.model.SemaPsgudInfoKorInfoResponseDataModel.Companion.toEntity
import com.happymoonday.data.model.SemaPsgudInfoKorInfoRowDataModel.DataMapper.fromEntity
import com.happymoonday.data.model.SemaPsgudInfoKorInfoRowDataModel.DataMapper.toEntity
import com.happymoonday.domain.model.SemaPsgudInfoKorInfoEntity
import com.happymoonday.domain.model.SemaPsgudInfoKorInfoRowEntity
import com.happymoonday.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Create Date: 2024. 11. 24.
 *
 * 미술품 정보 리스트  repository 구현제 이다.
 * @author LeeDongHun
 *
 *
 **/
class ProductRepositoryImpl @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) : ProductRepository {
    override suspend fun getSearchedProducts(
        startIndex: Int,
        endIndex: Int,
        category: String,
        manageYear: String,
        productNameKR: String
    ): Result<SemaPsgudInfoKorInfoEntity> =
        productRemoteDataSource.getSearchedProducts(
            startIndex = startIndex,
            endIndex = endIndex,
            category = category,
            manageYear = manageYear,
            productNameKR = productNameKR
        ).map { it.toEntity() }

    override suspend fun setBookMarkArtProduct(semaPsgudInfoKorInfoRowEntity: SemaPsgudInfoKorInfoRowEntity): Result<Unit> {
        return productLocalDataSource.setBookMarkArtProduct(semaPsgudInfoKorInfoRowEntity.fromEntity())
    }

    override suspend fun removeBookMarkArtProduct(productId: Long): Result<Unit> {
        return productLocalDataSource.removeBookMarkArtProduct(productId)
    }

    override suspend fun getBookMarkArtProductList(): Result<List<SemaPsgudInfoKorInfoRowEntity>> {
        return productLocalDataSource.getBookMarkArtProductList().map {
            it.map { it.toEntity() }
        }
    }
}
