package com.happymoonday.data.mapper

/**
 * Create Date: 2024. 11. 24.
 *
 * data 모듈용  mapper 이다.
 * domain에서 사용하는 entity로 변환한다.
 *  @author LeeDongHun
 *
 *
**/
internal interface ToEntityMapper<T, E> {
    fun T.toEntity(): E
}
