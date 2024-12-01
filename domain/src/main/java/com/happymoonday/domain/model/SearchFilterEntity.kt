package com.happymoonday.domain.model


/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 검색 필터를 구현할때 사용하는
 * 필터 타입들의 정의 이다.
 *
 * @param ProductionYear 제작년도 필터 타입
 * @param ArtCategory 예술 카테고리 필터 타입
 *
 * @author LeeDongHun
 *
 *
 **/
sealed interface SearchFilterType {

    /**
     * @param Ascending 제작년도 오름차순
     * @param Descending 제작년도 내림차순
     **/
    sealed interface ProductionYear : SearchFilterType {
        data object Ascending : ProductionYear
        data object Descending : ProductionYear
    }

    /**
     * @param Painting 회화
     * @param KoreanPainting 한국화
     * @param DrawingAndPrintMaking 드로잉&판화
     * @param Carve 조각
     * @param NewMedia 뉴미디어
     * @param Photo 사진
     * @param Install 설치
     * @param Design 디자인
     * @param Unknown 부문 미상
     **/
    sealed interface ArtCategory : SearchFilterType {
        data object Painting : ArtCategory
        data object KoreanPainting : ArtCategory
        data object DrawingAndPrintMaking : ArtCategory
        data object Carve : ArtCategory
        data object NewMedia : ArtCategory
        data object Photo : ArtCategory
        data object Install : ArtCategory
        data object Design : ArtCategory
        data object Unknown : ArtCategory
    }
}

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 검색 필터를 구현할때 사용하는 데이터들을 구성한다.
 * 추가되는 필터 타입들은 SearchFilterType에 추가후,
 * 아래에서 구현한다.
 *
 * 도메인 영역으로 판단되어 domain에서 설계
 *
 * 아래 영역은  필수적이므로 강제 구현
 * @param isSelected 필터 선택 여부
 * @param displayName 필터 이름
 * @param filterType 필터 타입
 *
 * @author LeeDongHun
 *
 *
**/
sealed interface SearchFilterEntity {
    var isSelected: Boolean
    var displayName: String
    val filterType: SearchFilterType

    /**
     * 제작년도 필터용 data 모델
     *
     * @param isSelected 오름차순 기본 선택
    **/
    data class ProductionYearFilter(
        override val filterType: SearchFilterType.ProductionYear,
        override var isSelected: Boolean = filterType == SearchFilterType.ProductionYear.Ascending,
        override var displayName: String = when (filterType) {
            SearchFilterType.ProductionYear.Ascending -> "제작년도 오름차순"
            SearchFilterType.ProductionYear.Descending -> "제작년도 내림차순"
        },
    ) : SearchFilterEntity


    /**
     * 카테고리 필터용 data 모델
     *
     * @param isSelected 카테고리는 기본적으로 전부 선택 처리
    **/
    data class ArtCategoryFilter(
        override val filterType: SearchFilterType.ArtCategory,
        override var isSelected: Boolean = true,
        override var displayName: String = when (filterType) {
            SearchFilterType.ArtCategory.Painting -> "회화"
            SearchFilterType.ArtCategory.KoreanPainting -> "한국화"
            SearchFilterType.ArtCategory.DrawingAndPrintMaking -> "드로잉&판화"
            SearchFilterType.ArtCategory.Carve -> "조각"
            SearchFilterType.ArtCategory.NewMedia -> "뉴미디어"
            SearchFilterType.ArtCategory.Photo -> "사진"
            SearchFilterType.ArtCategory.Install -> "설치"
            SearchFilterType.ArtCategory.Design -> "디자인"
            SearchFilterType.ArtCategory.Unknown -> "부문 미상"
        },
    ) : SearchFilterEntity {
        companion object {

            /**
             * @param artCategoryName 예술 카테고리 이름
             * @return SearchFilterType.ArtCategory 예술 카테고리 이름과 매칭되는 필터 타입 반환
            **/
            fun returnArtCategoryFilterType(artCategoryName: String): SearchFilterType.ArtCategory {
                return when (artCategoryName) {
                    "회화" -> SearchFilterType.ArtCategory.Painting
                    "한국화" -> SearchFilterType.ArtCategory.KoreanPainting
                    "드로잉&판화" -> SearchFilterType.ArtCategory.DrawingAndPrintMaking
                    "조각" -> SearchFilterType.ArtCategory.Carve
                    "뉴미디어" -> SearchFilterType.ArtCategory.NewMedia
                    "사진" -> SearchFilterType.ArtCategory.Photo
                    "설치" -> SearchFilterType.ArtCategory.Install
                    "디자인" -> SearchFilterType.ArtCategory.Design
                    "부문 미상" -> SearchFilterType.ArtCategory.Unknown
                    else -> SearchFilterType.ArtCategory.Unknown
                }
            }
        }
    }

    companion object {

        /**
         * 제작년도 필터 리스트틀 구성한후 반환한다.
         * presentation에서는 usecase를 통해서만 접근 가능하게 하기 위해, internal 처리
        **/
        internal fun getProductionYearFilterList(): List<ProductionYearFilter> {
            return listOf(
                SearchFilterType.ProductionYear.Ascending,
                SearchFilterType.ProductionYear.Descending
            ).map { ProductionYearFilter(filterType = it) }
        }

        /**
         * 예술 카테고리 필터 리스트를 구성한후 반환한다.
         * presentation에서는 usecase를 통해서만 접근 가능하게 하기 위해, internal 처리
        **/
        internal fun getArtCategoryFilterContentList(): List<ArtCategoryFilter> {
            return listOf(
                SearchFilterType.ArtCategory.Painting,
                SearchFilterType.ArtCategory.KoreanPainting,
                SearchFilterType.ArtCategory.DrawingAndPrintMaking,
                SearchFilterType.ArtCategory.Carve,
                SearchFilterType.ArtCategory.NewMedia,
                SearchFilterType.ArtCategory.Photo,
                SearchFilterType.ArtCategory.Install,
                SearchFilterType.ArtCategory.Design,
                SearchFilterType.ArtCategory.Unknown
            ).map { ArtCategoryFilter(filterType = it) }
        }
    }
}
