package com.happymoonday.presentation.model

import android.os.Parcelable
import com.happymoonday.domain.model.SemaPsgudInfoKorInfoEntity
import com.happymoonday.domain.model.SemaPsgudInfoKorInfoRowEntity
import com.happymoonday.presentation.mapper.FromEntityMapper
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel.Companion.fromEntity
import kotlinx.parcelize.Parcelize

/**
 * @param semaPsgudInfoList  미술품 정보 리스트
 */
@Parcelize
data class SemaPsgudInfoKorInfoUiModel(
    val semaPsgudInfoList: List<SemaPsgudInfoKorInfoRowUiModel>
) : Parcelable {
    internal companion object :
        FromEntityMapper<SemaPsgudInfoKorInfoUiModel, SemaPsgudInfoKorInfoEntity> {
        override fun SemaPsgudInfoKorInfoEntity.fromEntity(): SemaPsgudInfoKorInfoUiModel {
            return SemaPsgudInfoKorInfoUiModel(
                semaPsgudInfoList = semaPsgudInfoList.map { it.fromEntity() }
            )
        }
    }
}

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
@Parcelize
data class SemaPsgudInfoKorInfoRowUiModel(
    val productCategory: String,
    val dateOfMade: String,
    val dateOfCollection: String,
    val productName: String,
    val writerName: String,
    val productStandard: String,
    val materialTechInfo: String,
    val mainImage: String,
    val thumbNailImage: String
) : Parcelable {
    internal companion object :
        FromEntityMapper<SemaPsgudInfoKorInfoRowUiModel, SemaPsgudInfoKorInfoRowEntity> {
        override fun SemaPsgudInfoKorInfoRowEntity.fromEntity(): SemaPsgudInfoKorInfoRowUiModel {
            return SemaPsgudInfoKorInfoRowUiModel(
                productCategory = productCategory,
                dateOfMade = dateOfMade,
                dateOfCollection = dateOfCollection,
                productName = productName,
                writerName = writerName,
                productStandard = productStandard,
                materialTechInfo = materialTechInfo,
                mainImage = mainImage,
                thumbNailImage = thumbNailImage
            )
        }
    }
}

