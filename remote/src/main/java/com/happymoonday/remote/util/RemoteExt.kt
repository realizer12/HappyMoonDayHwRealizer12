package com.happymoonday.remote.util

import com.happymoonday.core.exception.HayMoonException
import retrofit2.Response

/**
 * Create Date: 2024. 11. 24.
 *
 * retrofit response를 Result로 변환
 * @author LeeDongHun
 *
**/
suspend fun <T, R> Response<T>.toResult(transform: (T) -> R): Result<R> {
    return try {
        when {
            isSuccessful -> {//status code 200~299일때 처리
                body()?.let {
                    Result.success(transform(it))
                } ?: throw HayMoonException.NonFatalException("response body is null")
            }
            else -> {//status code 200~299가 아닐때 처리 -
                throw HayMoonException.NonFatalException(errorBody()?.string() ?: "error body is null")
            }
        }
    } catch (e: Exception) {//혹시나 위에서 error 발생시 아래 exception으로 처리
        throw HayMoonException.NonFatalException(e.message ?: "unknown error")
    }
}
