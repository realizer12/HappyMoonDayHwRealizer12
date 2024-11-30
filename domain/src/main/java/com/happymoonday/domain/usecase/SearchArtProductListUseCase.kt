package com.happymoonday.domain.usecase

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

    private companion object {
        //검색 데이터 범위
        const val SEARCH_DATA_RANGE = 100
    }

    /**
     * 미술품 검색 리스트 조회
     *
     * @param startIndex 요청 시작 인덱스
     * @param endIndex 요청 종료 인덱스 -> 시작 index에서  SEARCH_DATA_RANGE 개씩 증가
     * @param category 부문
     * @param manageYear 수집년도
     * @param productNameKR 작품명(국문)
     **/
    suspend operator fun invoke(
        startIndex: Int,
        endIndex: Int = startIndex + SEARCH_DATA_RANGE,
        category: String = " ",
        manageYear: String = " ",
        productNameKR: String
    ) = productRepository.getSearchedProducts(
        startIndex = startIndex,
        endIndex = endIndex,
        category = category,
        manageYear = manageYear,
        productNameKR = productNameKR,
    ).map { response ->
        response.apply {
            searchDataNextStartIndex = endIndex//다음 페이징 시작 index는 현재 요청 endIndex + SEARCH_DATA_RANGE로 return해준다.
        }
    }

}
