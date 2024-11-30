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
 *
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
                if (startIndex == 0) {//첫번째 페이지에서 검색 결과 없는 경우 NO_SEARCHED_DATA_VIEW_SHOWN 보냄.
                    throw HayMoonException.UiHandlerException(
                        message = seoulApiResultCode.MESSAGE,
                        code = ClientHandleCodeType.NO_SEARCHED_DATA_VIEW_SHOWN
                    )
                } else {//마지막 페이지인 경우 토스트 exception으로 처리
                    throw HayMoonException.ToastException(
                        message = "마지막 페이지 입니다."
                    )
                }
            }
            else -> {
                throw HayMoonException.ToastException(
                    message = seoulApiResultCode.MESSAGE
                )
            }
        }
    }.recoverCatching {
        when (it) {
            is HayMoonException -> throw it//mapCatching에서 발생한 exception은 그대로 throw
            else -> throw HayMoonException.NonFatalException(it.message ?: "unknown error")//그외에는 nonFatalException으로 처리
        }
    }
}
