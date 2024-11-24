package com.happymoonday.remote.datasource.impl

import com.happymoonday.core.exception.ClientHandleCodeType
import com.happymoonday.core.exception.HayMoonException
import com.happymoonday.data.datasource.ProductRemoteDataSource
import com.happymoonday.data.model.SemaPsgudInfoKorInfoResponseDataModel
import com.happymoonday.remote.model.SemaPsgudInfoKorInfoResponseRemoteDataModel.Companion.toData
import com.happymoonday.remote.retrofit.ApiService
import com.happymoonday.remote.util.SeoulArtApiResponseType
import com.happymoonday.remote.util.toResult
import javax.inject.Inject

/**
 * Create Date: 2024. 11. 24.
 *
 *
 * 미술품 관련 remote 데이터소스 구현체
 * @author LeeDongHun
 *
 *
 **/
class ProductRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRemoteDataSource {
    override suspend fun getSearchedProducts(
        startIndex: Int,
        endIndex: Int,
        category: String,
        manageYear: String,
        productNameKR: String
    ): Result<SemaPsgudInfoKorInfoResponseDataModel> = runCatching {
        apiService.getArtworks(
            startIndex = startIndex,
            endIndex = endIndex,
            category = category,
            manageYear = manageYear,
            productNameKR = productNameKR
        ).toResult { it.toData() }.getOrThrow()//toResult success return이면 mapCatching으로 빠져서 -> api response code 체크
    }.mapCatching { result ->
        val seoulApiResultCode = result.semaPsgudInfoKorInfoResponse?.RESULT ?: run { result.RESULT }
        when (seoulApiResultCode.CODE) {
            SeoulArtApiResponseType.SUCCESS.code -> {//정상 처리일떄,
                result
            }
            SeoulArtApiResponseType.NO_DATA.code -> {//검색 결과가 없을때
                throw HayMoonException.UiHandlerException(
                    message = seoulApiResultCode.MESSAGE,
                    code = ClientHandleCodeType.NO_SEARCHED_DATA_VIEW_SHOWN
                )
            }
            else -> {
                throw HayMoonException.ToastException(
                    message = seoulApiResultCode.MESSAGE
                )
            }
        }
    }.recoverCatching {
        throw it
    }
}
