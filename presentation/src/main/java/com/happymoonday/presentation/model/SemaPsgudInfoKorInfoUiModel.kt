package com.happymoonday.presentation.model

import android.os.Parcelable
import com.happymoonday.domain.model.SemaPsgudInfoKorInfoEntity
import com.happymoonday.domain.model.SemaPsgudInfoKorInfoRowEntity
import com.happymoonday.presentation.mapper.FromEntityMapper
import com.happymoonday.presentation.mapper.ToEntityMapper
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel.Companion.fromEntity
import kotlinx.parcelize.Parcelize

/**
 * @param semaPsgudInfoList  미술품 정보 리스트
 * @param searchDataNextStartIndex 다음 페이징의 시작 인덱스
 */
@Parcelize
data class SemaPsgudInfoKorInfoUiModel(
    val semaPsgudInfoList: List<SemaPsgudInfoKorInfoRowUiModel>,
    var searchDataNextStartIndex:Int,
) : Parcelable {
    internal companion object :
        FromEntityMapper<SemaPsgudInfoKorInfoUiModel, SemaPsgudInfoKorInfoEntity> {
        override fun SemaPsgudInfoKorInfoEntity.fromEntity(): SemaPsgudInfoKorInfoUiModel {
            return SemaPsgudInfoKorInfoUiModel(
                semaPsgudInfoList = semaPsgudInfoList.map { it.fromEntity() },
                searchDataNextStartIndex = searchDataNextStartIndex
            )
        }
    }
}

/**
 * @param productCategory 미술품 카테고리(부문)
 * @param dateOfMade 제작년도
 * @param dateOfCollection 수집년도
 * @param productName 작품명
 * @param productNameEn 작품명 (영문)
 * @param writerName 작가명
 * @param productStandard 작품 구격
 * @param materialTechInfo 재료/기법
 * @param mainImage 메인이미지
 * @param thumbNailImage 썸네일이미지
 * @param isBookMarked 즐겨찾기 여부
 **/
@Parcelize
data class SemaPsgudInfoKorInfoRowUiModel(
    val id:Long = 0,
    val productCategory: String,
    val dateOfMade: String,
    val dateOfCollection: String,
    val productName: String,
    val productNameEn: String,
    val writerName: String,
    val productStandard: String,
    val materialTechInfo: String,
    val mainImage: String,
    val thumbNailImage: String,
    var isBookMarked:Boolean = false,
) : Parcelable {
    internal companion object :
        FromEntityMapper<SemaPsgudInfoKorInfoRowUiModel, SemaPsgudInfoKorInfoRowEntity>,ToEntityMapper<SemaPsgudInfoKorInfoRowEntity,SemaPsgudInfoKorInfoRowUiModel> {
        override fun SemaPsgudInfoKorInfoRowEntity.fromEntity(): SemaPsgudInfoKorInfoRowUiModel {
            return SemaPsgudInfoKorInfoRowUiModel(
                id = id,
                productCategory = productCategory,
                dateOfMade = dateOfMade,
                dateOfCollection = dateOfCollection,
                productName = productName,
                writerName = writerName,
                productStandard = productStandard,
                materialTechInfo = materialTechInfo,
                mainImage = mainImage,
                thumbNailImage = thumbNailImage,
                productNameEn = productNameEn
            )
        }

        override fun SemaPsgudInfoKorInfoRowUiModel.toEntity(): SemaPsgudInfoKorInfoRowEntity {
            return SemaPsgudInfoKorInfoRowEntity(
                id = id,
                productCategory = productCategory,
                dateOfMade = dateOfMade,
                dateOfCollection = dateOfCollection,
                productName = productName,
                writerName = writerName,
                productStandard = productStandard,
                materialTechInfo = materialTechInfo,
                mainImage = mainImage,
                thumbNailImage = thumbNailImage,
                productNameEn = productNameEn
            )
        }
    }
}

