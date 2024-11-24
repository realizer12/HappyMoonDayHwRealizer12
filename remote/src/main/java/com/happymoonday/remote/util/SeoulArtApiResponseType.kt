package com.happymoonday.remote.util


/**
 * 서울시 미술관련 데이터 관련 api response code 정의
 * haymoon 과제앱에서 예외처리 필요한  code 만 정의함.
 *
 * 비즈니스 로직 영역으로 판단되어 domain에 넣음.
 **/
enum class SeoulArtApiResponseType(val code: String) {

    /**
     * 서울시 미술관련 데이터 api 호출시 에러 발생
     **/
    SUCCESS("INFO-000"),

    /**
     * 해당 하는 데이터가 없는 경우
     **/
    NO_DATA("INFO-200"),
}
