package com.happymoonday.presentation.feature.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymoonday.domain.usecase.GetBookMarkListUseCase
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel.Companion.fromEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Create Date: 2024. 12. 2.
 *
 * 즐겨찾기 화면 ViewModel
 * @author LeeDongHun
 *
 *
**/
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getBookMarkListUseCase: GetBookMarkListUseCase,
):ViewModel() {

    //북마크 리스트 뷰로 보냄.
    private val _bookMarkList = MutableLiveData<List<SemaPsgudInfoKorInfoRowUiModel>>()
    val bookMarkList: LiveData<List<SemaPsgudInfoKorInfoRowUiModel>> = _bookMarkList

    init {
        getBookMarkList()
    }

    // 북마크 리스트 가져와서 현재
    //상세 화면 상품과 같은 것이 있는지 체크
    fun getBookMarkList() = viewModelScope.launch {
        getBookMarkListUseCase()
            .onSuccess {
                _bookMarkList.value = it.map { it.fromEntity() }.sortedByDescending { it.id }
            }
    }


}
