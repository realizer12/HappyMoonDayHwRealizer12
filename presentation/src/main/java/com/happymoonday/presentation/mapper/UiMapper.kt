package com.happymoonday.presentation.mapper

/**
 * Create Date: 2024. 11. 24.
 *
 * domain 모델을 presentation 모델로 바꿔주는 mapper이다.
 * @author LeeDongHun
 *
 *
**/
internal interface FromEntityMapper <T,E> {
    fun E.fromEntity():T
}
