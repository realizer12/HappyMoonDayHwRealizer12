package com.happymoonday.core.exception


/**
 * Create Date: 2024. 11. 24.
 *
 * haymoonday 과제 앱에서 발생하는 예외 처리를 위한 Exception 클래스
 *
 * @author LeeDongHun
 *
 **/
sealed class HayMoonException(message: String = "") : Exception(message) {

    /**
     * 심각하지 않은 예외 처리를 위한 Exception
     * firbase non fatal 을 주로 사용
     **/
    data class NonFatalException(override val message: String = "") :
        HayMoonException(message = message)

    /**
     * 토스트 메세지를 띄우기 위한 Exception
    **/
    data class ToastException(override val message: String = "") :
        HayMoonException(message = message)

    /**
     * ui 처리해줘야 하는 exception
    **/
    data class UiHandlerException(override val message: String = "",val code:Int) :
        HayMoonException(message = message)
}


/**
 * Create Date: 2024. 11. 24.
 *
 * ui 처리해줘야 하는 exception code
 *
 * 해당 코드에 따라 ui 처리해줘야 하는 로직을 처리한다.
 * @author LeeDongHun
 *
 *
**/
enum class ClientHandleCodeType(val code: Int) {

    /**
     * 검색 결과가 없음 뷰를 보여 줘야 할때
    **/
    NO_SEARCHED_DATA_VIEW_SHOWN(100)
}
