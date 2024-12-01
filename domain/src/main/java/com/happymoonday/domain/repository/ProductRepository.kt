package com.happymoonday.domain.repository

import com.happymoonday.domain.model.SemaPsgudInfoKorInfoEntity
import com.happymoonday.domain.model.SemaPsgudInfoKorInfoRowEntity

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

    /**
     * 소장품 즐겨찾기 설정
     **/
    suspend fun setBookMarkArtProduct(
        semaPsgudInfoKorInfoRowEntity:SemaPsgudInfoKorInfoRowEntity
    ):Result<Unit>

    /**
     * 소장품 즐겨찾기 해제
     *
     * @param productId 로컬 저장에 사용된  소장품 아이디를 보낸다
     **/
    suspend fun removeBookMarkArtProduct(
        productId:Long,
    ):Result<Unit>

    /**
     * 즐겨찾기한 소장품 리스트 가져오기
     **/
    suspend fun getBookMarkArtProductList():Result<List<SemaPsgudInfoKorInfoRowEntity>>
}

