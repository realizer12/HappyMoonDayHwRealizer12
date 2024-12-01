package com.happymoonday.domain.usecase

import com.happymoonday.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Create Date: 2024. 12. 1.
 *
 * Description : 즐겨찾기 리스트를 가져오는 유스케이스
 *
 * @author LeeDongHun
 *
 *
**/
class GetBookMarkListUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    suspend operator fun invoke() = productRepository.getBookMarkArtProductList()
}
