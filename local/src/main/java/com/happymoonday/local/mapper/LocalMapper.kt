package com.happymoonday.local.mapper

/**
 * lcaol data model을  data model로 변환하는 mapper
 **/
interface ToDataMapper<T, E> {
    fun T.toData(): E
}

interface FromDataMapper<T, E> {
    fun T.FromData(): E
}

