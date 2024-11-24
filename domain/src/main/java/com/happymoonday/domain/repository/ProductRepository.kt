package com.happymoonday.domain.repository

import com.happymoonday.domain.model.SemaPsgudInfoKorInfoEntity

/**
 * Create Date: 2024. 11. 24.
 *
 * 미술품 정보 리스트  repository interface 이다.
 * @author LeeDongHun
 *
 *
**/
interface ProductRepository {
    /**
     * parameter 정보에 따른 미술품 정보 조회
     *
     * @param startIndex 요청 시작 인덱스
     * @param endIndex 요청 종료 인덱스
     * @param category 부문
     * @param manageYear 수집년도
     * @param productNameKR 작품명(국문)
     **/
    suspend fun getSearchedProducts(
        startIndex: Int,
        endIndex: Int,
        category: String,
        manageYear: String,
        productNameKR: String
    ):Result<SemaPsgudInfoKorInfoEntity>
}

