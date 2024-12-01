package com.happymoonday.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.happymoonday.data.model.SemaPsgudInfoKorInfoRowDataModel
import com.happymoonday.local.mapper.FromDataMapper
import com.happymoonday.local.mapper.ToDataMapper

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
 **/
@Entity(
    tableName = "bookMarkedArtProductTable",
    indices = [Index(value = ["dateOfMade", "productName", "writerName"], unique = true)]
)
data class SemaPsgudInfoKorInfoRowLocalDataModel(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo("productCategory") val productCategory: String,
    @ColumnInfo("dateOfMade") val dateOfMade: String,
    @ColumnInfo("dateOfCollection") val dateOfCollection: String,
    @ColumnInfo("productName") val productName: String,
    @ColumnInfo("productNameEn") val productNameEn: String,
    @ColumnInfo("writerName") val writerName: String,
    @ColumnInfo("productStandard") val productStandard: String,
    @ColumnInfo("materialTechInfo") val materialTechInfo: String,
    @ColumnInfo("mainImage") val mainImage: String,
    @ColumnInfo("thumbNailImage") val thumbNailImage: String,
) {
    internal companion object :
        FromDataMapper<SemaPsgudInfoKorInfoRowDataModel, SemaPsgudInfoKorInfoRowLocalDataModel>,
        ToDataMapper<SemaPsgudInfoKorInfoRowLocalDataModel, SemaPsgudInfoKorInfoRowDataModel> {
        override fun SemaPsgudInfoKorInfoRowDataModel.FromData(): SemaPsgudInfoKorInfoRowLocalDataModel {
            return SemaPsgudInfoKorInfoRowLocalDataModel(
                id = id,
                productCategory = prdct_cl_nm ?: "부문 미상",
                dateOfMade = mnfct_year,
                dateOfCollection = manage_no_year ?: "수집년도 미상",
                productName = prdct_nm_korean ?: "작품명 미상",
                productNameEn = prdct_nm_eng ?: "no name",
                writerName = writr_nm,
                productStandard = prdct_stndrd,
                materialTechInfo = matrl_technic,
                mainImage = main_image,
                thumbNailImage = thumb_image,
            )
        }

        override fun SemaPsgudInfoKorInfoRowLocalDataModel.toData(): SemaPsgudInfoKorInfoRowDataModel {
            return SemaPsgudInfoKorInfoRowDataModel(
                id = id,
                prdct_cl_nm = productCategory,
                mnfct_year = dateOfMade,
                manage_no_year = dateOfCollection,
                prdct_nm_korean = productName,
                prdct_nm_eng = productNameEn,
                writr_nm = writerName,
                prdct_stndrd = productStandard,
                matrl_technic = materialTechInfo,
                main_image = mainImage,
                thumb_image = thumbNailImage,
                prdct_detail = null
            )
        }
    }
}

