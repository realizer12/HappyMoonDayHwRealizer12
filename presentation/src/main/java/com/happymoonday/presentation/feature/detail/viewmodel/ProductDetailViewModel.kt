package com.happymoonday.presentation.feature.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.happymoonday.presentation.feature.search.activity.SearchActivity
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Create Date: 2024. 12. 1.
 *
 * Description: 상품 상세 화면 ViewModel
 *
 * @author LeeDongHun
 *
 *
**/
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
  private val savedStateHandle: SavedStateHandle
):ViewModel(){

  // 상품 상세 정보
  private val productDetailInfo = savedStateHandle.get<SemaPsgudInfoKorInfoRowUiModel>(SearchActivity.CLICKED_ART_PRODUCT_INFO)

  fun getProductDetailInfo(): SemaPsgudInfoKorInfoRowUiModel? {
    return productDetailInfo
  }

}
