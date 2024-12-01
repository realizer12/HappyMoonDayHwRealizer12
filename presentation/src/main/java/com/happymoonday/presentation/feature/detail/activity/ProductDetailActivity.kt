package com.happymoonday.presentation.feature.detail.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.happymoonday.design.compose.theme.HayMoonTheme
import com.happymoonday.presentation.feature.detail.screen.ArtProductDetailRoute
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel

/**
 * Create Date: 2024. 12. 1.
 *
 * 작품 리스트에서 작품을 눌렀을때
 * 들어가지는 상세 화면이다.
 *
 * 요구사항에 따라 compose 구성된다.
 *
 * @author LeeDongHun
 *
 *
 **/
class ProductDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HayMoonTheme {
                ArtProductDetailRoute(
                    semaPsgudInfoKorInfoRowUiModel = SemaPsgudInfoKorInfoRowUiModel(
                        productName = "상품 이름",
                        productCategory = "상품 카테고리",
                        productNameEn = "상품 영문 이름",
                        productStandard = "상품 규격",
                        dateOfCollection = "수집일자",
                        materialTechInfo = "재료 및 기술 정보",
                        dateOfMade = "제작년도",
                        writerName = "작가 이름",
                        mainImage = "메인 이미지",
                        thumbNailImage = "썸네일 이미지"
                    ),
                    onBackBtnClicked = { finish() }
                )
            }
        }
    }
}
