package com.happymoonday.domain.model

/**
 * @param semaPsgudInfoList  미술품 정보 리스트
 */
data class SemaPsgudInfoKorInfoEntity(
    val semaPsgudInfoList: List<SemaPsgudInfoKorInfoRowEntity>
)

/**
 * @param productCategory 미술품 카테고리(부문)
 * @param dateOfMade 제작년도
 * @param dateOfCollection 수집년도
 * @param productName 작품명
 * @param writerName 작가명
 * @param productStandard 작품 구격
 * @param materialTechInfo 재료/기법
 * @param mainImage 메인이미지
 * @param thumbNailImage 썸네일이미지
 **/
data class SemaPsgudInfoKorInfoRowEntity(
    val productCategory: String,
    val dateOfMade: String,
    val dateOfCollection: String,
    val productName: String,
    val writerName: String,
    val productStandard: String,
    val materialTechInfo: String,
    val mainImage: String,
    val thumbNailImage: String
)
