package com.happymoonday.domain.usecase

import com.happymoonday.core.exception.ClientHandleCodeType
import com.happymoonday.core.exception.HayMoonException
import com.happymoonday.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Create Date: 2024. 11. 24.
 *
 *
 * 미술품을 검색해서 해당 리스트를 받아올때 사용한는  usecase 이다.
 * @author LeeDongHun
 *
 *
 **/
class SearchArtProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(
        startIndex: Int,
        endIndex: Int,
        category: String = " ",
        manageYear: String = " ",
        productNameKR: String
    ) = productRepository.getSearchedProducts(
        startIndex = startIndex,
        endIndex = endIndex,
        category = category,
        manageYear = manageYear,
        productNameKR = productNameKR
    ).map { response ->
        response.semaPsgudInfoList.ifEmpty {
            throw HayMoonException.UiHandlerException(code = ClientHandleCodeType.NO_SEARCHED_DATA_VIEW_SHOWN)
        }
        response
    }
}
