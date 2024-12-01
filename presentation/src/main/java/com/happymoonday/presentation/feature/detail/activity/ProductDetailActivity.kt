package com.happymoonday.presentation.feature.detail.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.happymoonday.design.compose.theme.HayMoonTheme
import com.happymoonday.presentation.feature.detail.screen.ArtProductDetailRoute
import com.happymoonday.presentation.feature.detail.viewmodel.ProductDetailViewModel
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel
import com.happymoonday.presentation.util.SingleEventObserver
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
            val artProductInfo = rememberSaveable {
                mutableStateOf<SemaPsgudInfoKorInfoRowUiModel?>(null)
            }
            LaunchedEffect(Unit) {

                //상세 상품  정보 가져오기
                productDetailViewModel.productDetailInfo.observe(this@ProductDetailActivity) {
                    artProductInfo.value = it
                }

                //북마크 성공시 처리
                productDetailViewModel.isBookMarkSuccess.observe(this@ProductDetailActivity,SingleEventObserver {
                   if(it){
                       setResult(BOOK_MARK_FINISH_RESULT_CODE)
                       finish()
                   }
                })

                //북마크 삭제 성공시 처리
                productDetailViewModel.isDeleteBookMarkSuccess.observe(this@ProductDetailActivity,SingleEventObserver {
                    if(it){
                        setResult(BOOK_MARK_FINISH_RESULT_CODE)
                        finish()
                    }
                })
            }
            HayMoonTheme {
                artProductInfo.value?.let {
                    ArtProductDetailRoute(
                        semaPsgudInfoKorInfoRowUiModel = it,
                        onBackBtnClicked = {//뒤로가기 처리
                            finish()
                        },
                        onBookMarkClicked = { clickedArtProductInfo ->
                            productDetailViewModel.setBookMarkArtProduct(clickedArtProductInfo)
                        },
                        onBookMarkDeleteClicked = {
                            productDetailViewModel.removeBookMarkArtProduct(it.id)
                        }
                    )
                }
            }
        }
    }
}
