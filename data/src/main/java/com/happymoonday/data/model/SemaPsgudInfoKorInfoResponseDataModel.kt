package com.happymoonday.data.model

import com.happymoonday.data.mapper.ToEntityMapper
import com.happymoonday.data.model.SemaPsgudInfoKorInfoRowDataModel.DataMapper.toEntity
import com.happymoonday.domain.model.SemaPsgudInfoKorInfoEntity
import com.happymoonday.domain.model.SemaPsgudInfoKorInfoRowEntity

/**
 * data 레이어쪽 response data model
 *
 * @param semaPsgudInfoKorInfoResponse 미술품 정보 리스트
 * @param RESULT 응답결과 - 검색어 등이 없을때, RESULT만 옴으로 추가.
 */
data class SemaPsgudInfoKorInfoResponseDataModel(
    val semaPsgudInfoKorInfoResponse: SemaPsgudInfoKorInfoDataModel?,
    val RESULT: ResultDataModel,
){
    internal companion object : ToEntityMapper<SemaPsgudInfoKorInfoResponseDataModel,SemaPsgudInfoKorInfoEntity>{
        override fun SemaPsgudInfoKorInfoResponseDataModel.toEntity(): SemaPsgudInfoKorInfoEntity {
            return SemaPsgudInfoKorInfoEntity(
                semaPsgudInfoList = semaPsgudInfoKorInfoResponse?.rowDataModel?.map { it.toEntity() }?: emptyList()
            )
        }
    }
}

/**
 * @param list_total_count 전체 데이터 수
 * @param RESULT 응답결과
 * @param rowDataModel 미술품 정보 리스트
 */
data class SemaPsgudInfoKorInfoDataModel(
    val list_total_count: Int,
    val RESULT: ResultDataModel,
    val rowDataModel: List<SemaPsgudInfoKorInfoRowDataModel>,
)

/**
 * @param CODE 응답코드
 * @param MESSAGE 응답메시지
 */
data class ResultDataModel(
    val CODE: String,
    val MESSAGE: String,
)

/**
 * @param prdct_cl_nm 부문
 * @param manage_no_year 수집연도
 * @param prdct_nm_korean 작품명(국문)
 * @param prdct_nm_eng 작품명(영문)
 * @param prdct_stndrd 작품규격
 * @param mnfct_year 제작년도
 * @param matrl_technic 재료/기법
 * @param prdct_detail 작품설명
 * @param writr_nm 작가명
 * @param main_image 메인이미지
 * @param thumb_image 썸네일이미지
 **/
data class SemaPsgudInfoKorInfoRowDataModel(
    val prdct_cl_nm: String?,
    val manage_no_year: String?,
    val prdct_nm_korean: String?,
    val prdct_nm_eng: String?,
    val prdct_stndrd: String,
    val mnfct_year: String,
    val matrl_technic: String,
    val prdct_detail: String?,
    val writr_nm: String,
    val main_image: String,
    val thumb_image: String,
) {
    internal companion object DataMapper :
        ToEntityMapper<SemaPsgudInfoKorInfoRowDataModel, SemaPsgudInfoKorInfoRowEntity> {
        override fun SemaPsgudInfoKorInfoRowDataModel.toEntity(): SemaPsgudInfoKorInfoRowEntity {
            return SemaPsgudInfoKorInfoRowEntity(
                productCategory = prdct_cl_nm?:"부문 미상",
                dateOfCollection = manage_no_year?:"수집년도 미상",
                productName = prdct_nm_korean?:"작품명 미상",
                writerName = writr_nm,
                productStandard = prdct_stndrd,
                dateOfMade = mnfct_year,
                materialTechInfo = matrl_technic,
                mainImage = main_image,
                thumbNailImage = thumb_image
            )
        }
    }
}
