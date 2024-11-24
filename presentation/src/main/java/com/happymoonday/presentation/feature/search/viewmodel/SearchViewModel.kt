package com.happymoonday.presentation.feature.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymoonday.core.exception.ClientHandleCodeType
import com.happymoonday.core.exception.HayMoonException
import com.happymoonday.domain.usecase.SearchArtProductListUseCase
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoUiModel.Companion.fromEntity
import com.happymoonday.presentation.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Create Date: 2024. 11. 25.
 *
 * 검색화면 뷰모델
 * @author LeeDongHun
 *
**/
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArtProductListUseCase: SearchArtProductListUseCase
) : ViewModel() {

    //검색된 미술품 리스트 LiveData
    private val _searchArtProductList = MutableLiveData<List<SemaPsgudInfoKorInfoRowUiModel>>()
    val searchArtProductList: LiveData<List<SemaPsgudInfoKorInfoRowUiModel>> = _searchArtProductList

    //progress 이벤트 LiveData
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> = _progress

    //error 이벤트 LiveData
    private val _errorToast = MutableLiveData<SingleEvent<String>>()
    val errorToast: LiveData<SingleEvent<String>> = _errorToast

    private val _clientHandleError = MutableLiveData<SingleEvent<ClientHandleCodeType>>()
    val clientHandleError: LiveData<SingleEvent<ClientHandleCodeType>> = _clientHandleError

    fun searchArtProductList(keyword: String) {
        showProgress(isShow = true)
        //검색된 리스트 초기화
        clearSearchedData()
        viewModelScope.launch {
            searchArtProductListUseCase(
                startIndex = 0,
                endIndex = 10,
                productNameKR = keyword
            ).map {
                it.fromEntity()
            }.onSuccess {
                showProgress(isShow = false)
                _searchArtProductList.value = it.semaPsgudInfoList
            }.onFailure {
                showProgress(isShow = false)
                when(it){
                    is HayMoonException.UiHandlerException -> _clientHandleError.value = SingleEvent(it.code)
                    is HayMoonException.ToastException -> _errorToast.value = SingleEvent(it.message)
                    else -> println("error : ${it.message}")
                }
            }
        }
    }

    private fun showProgress(isShow: Boolean){
        _progress.value = isShow
    }

    //검색된 리스트 초기화
    private fun clearSearchedData(){
        _searchArtProductList.value = emptyList()
    }
}
