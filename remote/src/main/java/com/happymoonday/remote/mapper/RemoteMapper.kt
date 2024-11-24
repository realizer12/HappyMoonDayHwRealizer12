package com.happymoonday.remote.mapper

/**
 * remote data model을  data model로 변환하는 mapper
**/
interface ToDataMapper<T, E> {
    fun T.toData(): E
}
