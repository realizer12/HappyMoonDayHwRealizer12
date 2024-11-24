package com.happymoonday.remote.retrofit

import com.happymoonday.remote.model.SemaPsgudInfoKorInfoResponseRemoteDataModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Create Date: 2024. 11. 24.
 *
 * haymoon 과제 앱 Remote  api 정의
 *
 * @author LeeDongHun
 *
 *
 **/
interface ApiService {

    /**
     * 서울시립미술관 소장품 정보 공공 API 호출
     *
     * @param authenticationKey 인증키
     * @param startIndex 요청 시작 인덱스
     * @param endIndex 요청 종료 인덱스
     * @param category 부문
     * @param manageYear 수집년도
     * @param productNameKR 작품명(국문)
     *
    **/
    @GET("{key}/json/SemaPsgudInfoKorInfo/{startIndex}/{endIndex}/{category}/{manageYear}/{productNameKR}")
    suspend fun getArtworks(
        @Path("key") authenticationKey: String,
        @Path("startIndex") startIndex: Int,
        @Path("endIndex") endIndex: Int,
        @Path("category") category: String,
        @Path("manageYear") manageYear: String,
        @Path("productNameKR") productNameKR: String
    ):Result<SemaPsgudInfoKorInfoResponseRemoteDataModel>
}
