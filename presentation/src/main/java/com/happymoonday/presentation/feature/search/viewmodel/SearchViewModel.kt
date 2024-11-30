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

    //검색 데이터 시작 인덱스
    private var searchDataStartIndex = 0

    //이전 검색어 - 페이징시 계속 사용
    private var pastSearchKeyWord:String = ""

    /**
     * 미술품 검색 리스트 조회
     *
     * @param keyword 검색어 -> 기본값을 이전 pastSearchKeyWord로 설정하여, 다음 페이지 요청시에는 이전 검색어로 계속 요청
     * @param isNextPageRequest  다음 페이지 요청이 아닌 경우 검색어 초기화 및 startIndex 초기화, 검색에서는 그냥 keyword만 넣어주게 default false로 적용
     **/
    fun searchArtProductList(
        keyword: String = pastSearchKeyWord,
        isNextPageRequest: Boolean = false
    ) {
        //기존 리스틀 가져옴. null이면 emptyList()
        var currentList = _searchArtProductList.value ?: emptyList()

        //다음 페이지 요청이 아니라면 검색 다시 하는 것이므로,
        //1. startIndex 초기화 및 이전 검색어 초기화
        //2. currentList도 emptylist로 초기화
        if (!isNextPageRequest) {
            pastSearchKeyWord = keyword
            searchDataStartIndex = 0
            currentList = emptyList()
        }

        viewModelScope.launch {
            showProgress(isShow = isNextPageRequest.not())//다음 페이지 요청시에는 progress bar 보여주지 않음.
            searchArtProductListUseCase(
                startIndex = searchDataStartIndex,
                productNameKR = keyword
            ).mapCatching {
                it.fromEntity()
            }.onSuccess {
                showProgress(isShow = false)
                searchDataStartIndex = it.searchDataNextStartIndex //다음 페이징 start index 넣어줌

                //현재 리스트에서 새로운 list 추가 해서 뷰로 보냄.
                _searchArtProductList.value = currentList + it.semaPsgudInfoList
            }.onFailure {
                showProgress(isShow = false)
                when (it) {
                    is HayMoonException.UiHandlerException -> {
                        if (it.code == ClientHandleCodeType.NO_SEARCHED_DATA_VIEW_SHOWN) {
                            clearSearchedDataWhenNoSearchResult()
                        }
                    }
                    is HayMoonException.ToastException -> _errorToast.value = SingleEvent(it.message)
                    is HayMoonException.NonFatalException -> println("error : ${it.message}")
                }
            }
        }
    }

    //progress bar 보여주기 여부
    private fun showProgress(isShow: Boolean){
        _progress.value = isShow
    }

    //검색된 리스트 초기화
    private fun clearSearchedDataWhenNoSearchResult(){
        _searchArtProductList.value = emptyList()
    }
}
