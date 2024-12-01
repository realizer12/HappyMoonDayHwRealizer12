package com.happymoonday.presentation.feature.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymoonday.domain.usecase.BookMarkUseCase
import com.happymoonday.domain.usecase.DeleteBookMarkUseCase
import com.happymoonday.domain.usecase.GetBookMarkListUseCase
import com.happymoonday.presentation.feature.search.activity.SearchActivity
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel.Companion.fromEntity
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel.Companion.toEntity
import com.happymoonday.presentation.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
  private val savedStateHandle: SavedStateHandle,
  private val bookMarkUseCase: BookMarkUseCase,
  private val deleteBookMarkUseCase: DeleteBookMarkUseCase,
  private val getBookMarkListUseCase: GetBookMarkListUseCase,
) : ViewModel() {

  // 상품 상세 정보
  private val passedProductDetailInfo =
    savedStateHandle.get<SemaPsgudInfoKorInfoRowUiModel>(SearchActivity.CLICKED_ART_PRODUCT_INFO)

  //상품 보냄
  private val _productDetailInfo = MutableLiveData<SemaPsgudInfoKorInfoRowUiModel>()
  val productDetailInfo: LiveData<SemaPsgudInfoKorInfoRowUiModel> = _productDetailInfo

  //상품 즐겨찾기 성공
  private val _isBookMarkSuccess = MutableLiveData<SingleEvent<Boolean>>()
  val isBookMarkSuccess: LiveData<SingleEvent<Boolean>> = _isBookMarkSuccess

  //상품 즐겨찾기 취소 성공
  private val _isDeleteBookMarkSuccess = MutableLiveData<SingleEvent<Boolean>>()
  val isDeleteBookMarkSuccess: LiveData<SingleEvent<Boolean>> = _isDeleteBookMarkSuccess

  init {
    getBookMarkList()
  }


  // 북마크 리스트 가져와서 현재
  //상세 화면 상품과 같은 것이 있는지 체크
  private fun getBookMarkList() = viewModelScope.launch {
    getBookMarkListUseCase()
      .onSuccess {
        val result = it.map { it.fromEntity() }
        val matchedItem = result.find { bookmarkedItem ->
          bookmarkedItem.productCategory == passedProductDetailInfo?.productCategory &&
                  bookmarkedItem.writerName == passedProductDetailInfo.writerName &&
                  bookmarkedItem.productName == passedProductDetailInfo.productName &&
                  bookmarkedItem.dateOfMade == passedProductDetailInfo.dateOfMade
        }

        // 현재 상세 정보와 일치하는 북마크 찾기
        val isBookmarked = matchedItem != null
        _productDetailInfo.value = passedProductDetailInfo?.copy(id = matchedItem?.id?:0, isBookMarked = isBookmarked)
      }
  }

  //상품 북마크 추가
  fun setBookMarkArtProduct(
    semaPsgudInfoKorInfoRowUiModel: SemaPsgudInfoKorInfoRowUiModel
  ) = viewModelScope.launch {
    bookMarkUseCase(semaPsgudInfoKorInfoRowUiModel.toEntity())
      .onSuccess {
        _isBookMarkSuccess.value = SingleEvent(true)
      }
  }

  //북마크 삭제 성공 여부
  fun removeBookMarkArtProduct(productId: Long) = viewModelScope.launch {
    deleteBookMarkUseCase(productId)
      .onSuccess {
        _isDeleteBookMarkSuccess.value = SingleEvent(true)
      }
  }
}
