package com.happymoonday.presentation.feature.detail.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.happymoonday.design.compose.theme.HayMoonTheme
import com.happymoonday.presentation.feature.detail.screen.ArtProductDetailRoute
import com.happymoonday.presentation.feature.detail.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

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
@AndroidEntryPoint
class ProductDetailActivity : ComponentActivity() {

    // 상품 상세 화면 ViewModel
    private val productDetailViewModel:ProductDetailViewModel by viewModels()

    companion object {
        // 북마크 했을때 결과 코드
        const val BOOK_MARK_FINISH_RESULT_CODE = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HayMoonTheme {
                productDetailViewModel.getProductDetailInfo()?.let { productDetailInfo ->
                    ArtProductDetailRoute(
                        semaPsgudInfoKorInfoRowUiModel = productDetailInfo,
                        onBackBtnClicked = {//뒤로가기 처리
                            finish()
                        },
                        onBookMarkClicked = { clickedArtProductInfo ->
                            // 북마크 실행시
                            Toast.makeText(this, clickedArtProductInfo.productName, Toast.LENGTH_SHORT).show()
                            setResult(BOOK_MARK_FINISH_RESULT_CODE)
                            finish()

                        }
                    )
                }
            }
        }
    }
}
