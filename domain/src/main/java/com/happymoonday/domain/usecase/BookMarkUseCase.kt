package com.happymoonday.domain.usecase

import com.happymoonday.domain.model.SemaPsgudInfoKorInfoRowEntity
import com.happymoonday.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Create Date: 2024. 12. 1.
 *
 * Description : 즐겨찾기 처리를 할때 사용하는 유스케이스
 * @author LeeDongHun
 *
 *
 **/
class BookMarkUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(
        semaPsgudInfoKorInfoRowEntity: SemaPsgudInfoKorInfoRowEntity
    ) = productRepository.setBookMarkArtProduct(semaPsgudInfoKorInfoRowEntity)
}
