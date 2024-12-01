package com.happymoonday.presentation.feature.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happymoonday.core.exception.ClientHandleCodeType
import com.happymoonday.core.exception.HayMoonException
import com.happymoonday.domain.model.SearchFilterEntity
import com.happymoonday.domain.model.SearchFilterType
import com.happymoonday.domain.usecase.GetArtCategoryFilterUseCase
import com.happymoonday.domain.usecase.GetProductionYearFilterUseCase
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
    private val searchArtProductListUseCase: SearchArtProductListUseCase,
    private val getArtCategoryFilterUseCase: GetArtCategoryFilterUseCase,
    private val getProductionYearFilterUseCase: GetProductionYearFilterUseCase,
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

    //제작년도 필터 데이터 리스트
    private val productionYearFilterList = getProductionYearFilterUseCase()

    //미술품 카테고리 필터 데이터 리스트
    private val artCategoryFilterList = getArtCategoryFilterUseCase()

    //제작년도 필터 뷰에 보이는 문구 처리 LiveData
    private val _productYearFilterString = MutableLiveData<String>()
    val productYearFilterString: LiveData<String> = _productYearFilterString

    //카테고리 필터 뷰에 보이는 문구 처리 LiveData
    private val _artCategoryFilterString = MutableLiveData<String>()
    val artCategoryFilterString: LiveData<String> = _artCategoryFilterString

    init {
        initSetFilterData()
    }

    //필터 data 초기세팅
    private fun initSetFilterData() {
        _productYearFilterString.value = productionYearFilterList.find { it.isSelected }?.displayName?:"error"
        _artCategoryFilterString.value = artCategoryFilterList.returnArtCategoryFilerString()
    }

    /**
     * 재작년도 필터를 구성하는 데이터 리스트 return
    **/
    fun getProductYearFilterList():  List<SearchFilterEntity.ProductionYearFilter> {
        return productionYearFilterList
    }

    /**
     * 재작년도 선택시 수정사항들 처리
    **/
    fun setSelectedProductionFilterType(selectedProductionYearFilter: SearchFilterEntity.ProductionYearFilter) {

        //이미 선택된 값이면 early return - 로직 안돌게 하기
        if(productionYearFilterList.find { it.isSelected } == selectedProductionYearFilter) return

        //기존 selected값 변경
        productionYearFilterList.forEach {
            it.isSelected = it == selectedProductionYearFilter
        }

        //제작년도 필터 뷰에 보이는 문구 변경
        _productYearFilterString.value = selectedProductionYearFilter.displayName

        //현재 list기준으로 filter값 적용
        val currentList = _searchArtProductList.value ?: emptyList()
        _searchArtProductList.value =  currentList.ifEmpty { return }.sortArtListWithProductionFilter()
    }

    //가장 최신 재작년도 필터 기준으로 sort 처리 진행
    private fun List<SemaPsgudInfoKorInfoRowUiModel>.sortArtListWithProductionFilter():List<SemaPsgudInfoKorInfoRowUiModel>{
        return this.let {
            val productionYearFilterType = productionYearFilterList.find { it.isSelected }?.filterType
            when(productionYearFilterType){
                SearchFilterType.ProductionYear.Ascending -> it.sortedBy { it.dateOfMade }
                else-> it.sortedByDescending { it.dateOfMade }
            }
        }
    }

    //ArtCategoryFilter에 한하여, 전체 선택인 경우 전체를 return
    //그외에는 선택된 것만 return
    private fun List<SearchFilterEntity.ArtCategoryFilter>.returnArtCategoryFilerString(): String {
        return if (all { it.isSelected }) "전체"
        else filter { it.isSelected }
            .joinToString(", ") { it.displayName}
    }

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
                //새로운 리스트 기준 필터처리 계속 적용
                _searchArtProductList.value = (currentList + it.semaPsgudInfoList).sortArtListWithProductionFilter()
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
