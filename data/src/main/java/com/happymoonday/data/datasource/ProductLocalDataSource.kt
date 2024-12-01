package com.happymoonday.data.datasource

import com.happymoonday.data.model.SemaPsgudInfoKorInfoRowDataModel

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 상품 로컬 데이터 소스
 *
 * @author LeeDongHun
 *
 *
**/
interface ProductLocalDataSource {

    /**
     * 소장품 즐겨찾기 설정
    **/
    suspend fun setBookMarkArtProduct(
        semaPsgudInfoKorInfoRowDataModel:SemaPsgudInfoKorInfoRowDataModel
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
    suspend fun getBookMarkArtProductList():Result<List<SemaPsgudInfoKorInfoRowDataModel>>
}
