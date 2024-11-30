package com.happymoonday.remote.model

import com.happymoonday.data.model.ResultDataModel
import com.happymoonday.data.model.SemaPsgudInfoKorInfoDataModel
import com.happymoonday.data.model.SemaPsgudInfoKorInfoResponseDataModel
import com.happymoonday.data.model.SemaPsgudInfoKorInfoRowDataModel
import com.happymoonday.remote.mapper.ToDataMapper
import com.happymoonday.remote.model.ResultRemoteDataModel.Companion.toData
import com.happymoonday.remote.model.SemaPsgudInfoKorInfoRemoteDataModel.Companion.toData
import com.happymoonday.remote.model.SemaPsgudInfoKorInfoRowRemoteDataModel.Companion.toData
import com.happymoonday.remote.util.SeoulArtApiResponseType

/**
 * remote datasource 쪽 response data model
 *
 * @param SemaPsgudInfoKorInfo 미술품 정보 리스트
 * @param RESULT 응답결과 - 검색어 등이 없을때, RESULT만 옴으로 추가
 *
 **/
data class SemaPsgudInfoKorInfoResponseRemoteDataModel(
    val SemaPsgudInfoKorInfo: SemaPsgudInfoKorInfoRemoteDataModel?,
    val RESULT: ResultRemoteDataModel?,
) {
    internal companion object :
        ToDataMapper<SemaPsgudInfoKorInfoResponseRemoteDataModel, SemaPsgudInfoKorInfoResponseDataModel> {
        override fun SemaPsgudInfoKorInfoResponseRemoteDataModel.toData(): SemaPsgudInfoKorInfoResponseDataModel {
            return SemaPsgudInfoKorInfoResponseDataModel(
                semaPsgudInfoKorInfoResponse = SemaPsgudInfoKorInfo?.toData(),
                RESULT = RESULT?.toData() ?: ResultDataModel(
                    SeoulArtApiResponseType.NO_DATA.code,
                    "검색 결과가 없습니다."
                )
            )
        }
    }
}

/**
 * @param list_total_count 전체 데이터 수
 * @param RESULT 응답결과
 * @param row 미술품 정보 리스트
 */
data class SemaPsgudInfoKorInfoRemoteDataModel(
    val list_total_count: Int,
    val RESULT: ResultRemoteDataModel,
    val row: List<SemaPsgudInfoKorInfoRowRemoteDataModel>,
) {
    internal companion object :
        ToDataMapper<SemaPsgudInfoKorInfoRemoteDataModel, SemaPsgudInfoKorInfoDataModel> {
        override fun SemaPsgudInfoKorInfoRemoteDataModel.toData(): SemaPsgudInfoKorInfoDataModel {
            return SemaPsgudInfoKorInfoDataModel(
                list_total_count = list_total_count,
                RESULT = RESULT.toData(),
                rowDataModel = row.map { it.toData() }
            )
        }
    }
}

/**
 * @param CODE 응답코드
 * @param MESSAGE 응답메시지
 */
data class ResultRemoteDataModel(
    val CODE: String,
    val MESSAGE: String,
) {
    internal companion object : ToDataMapper<ResultRemoteDataModel, ResultDataModel> {
        override fun ResultRemoteDataModel.toData(): ResultDataModel {
            return ResultDataModel(
                CODE = CODE,
                MESSAGE = MESSAGE
            )
        }
    }
}

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
data class SemaPsgudInfoKorInfoRowRemoteDataModel(
    val prdct_stndrd: String,
    val mnfct_year: String,
    val matrl_technic: String,
    val writr_nm: String,
    val main_image: String,
    val thumb_image: String,
    val prdct_cl_nm: String?,
    val manage_no_year: String?,
    val prdct_nm_korean: String?,
    val prdct_nm_eng: String?,
    val prdct_detail: String?,
) {
    internal companion object :
        ToDataMapper<SemaPsgudInfoKorInfoRowRemoteDataModel, SemaPsgudInfoKorInfoRowDataModel> {
        override fun SemaPsgudInfoKorInfoRowRemoteDataModel.toData(): SemaPsgudInfoKorInfoRowDataModel {
            return SemaPsgudInfoKorInfoRowDataModel(
                prdct_cl_nm = prdct_cl_nm,
                manage_no_year = manage_no_year,
                prdct_nm_korean = prdct_nm_korean,
                prdct_nm_eng = prdct_nm_eng,
                prdct_stndrd = prdct_stndrd,
                mnfct_year = mnfct_year,
                matrl_technic = matrl_technic,
                prdct_detail = prdct_detail,
                writr_nm = writr_nm,
                main_image = main_image,
                thumb_image = thumb_image
            )
        }
    }
}
