package com.happymoonday.domain.usecase

import com.happymoonday.domain.model.SearchFilterEntity
import com.happymoonday.domain.model.SearchFilterEntity.Companion.getArtCategoryFilterContentList
import javax.inject.Inject

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 예술 카테고리 필터를 가져오는 유스케이스
 *
 * @author LeeDongHun
 *
 *
 **/
class GetArtCategoryFilterUseCase @Inject constructor() {
    operator fun invoke(): List<SearchFilterEntity.ArtCategoryFilter> =
        getArtCategoryFilterContentList()
}
