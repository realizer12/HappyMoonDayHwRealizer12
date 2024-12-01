package com.happymoonday.domain.usecase

import com.happymoonday.domain.model.SearchFilterEntity
import com.happymoonday.domain.model.SearchFilterEntity.Companion.getProductionYearFilterList
import javax.inject.Inject

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 제작년도 필터 리스트를 가져오는 유스케이스
 *
 * @author LeeDongHun
 *
 *
 **/
class GetProductionYearFilterUseCase @Inject constructor() {
    operator fun invoke(): List<SearchFilterEntity.ProductionYearFilter> =
        getProductionYearFilterList()
}
