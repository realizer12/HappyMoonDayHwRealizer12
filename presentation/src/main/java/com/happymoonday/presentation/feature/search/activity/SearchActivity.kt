package com.happymoonday.presentation.feature.search.activity

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.happymoonday.core.exception.ClientHandleCodeType
import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseActivity
import com.happymoonday.presentation.databinding.ActivitySearchBinding
import com.happymoonday.presentation.feature.search.adapter.SearchItemRvAdapter
import com.happymoonday.presentation.feature.search.viewmodel.SearchViewModel
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel
import com.happymoonday.presentation.util.SingleEventObserver
import com.happymoonday.presentation.util.hideSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint

/**
 * Create Date: 2024. 11. 24.
 *
 * 검색 화면
 * @author LeeDongHun
 *
 *
**/
@AndroidEntryPoint
class SearchActivity:BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {

    private lateinit var searchItemRvAdapter: SearchItemRvAdapter
    private val searchViewModel: SearchViewModel by viewModels()

    override fun ActivitySearchBinding.onCreate() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }
    private fun initSet(){
        searchItemRvAdapter = SearchItemRvAdapter()
        binding.rvSearchedArtProductList.apply {
            adapter = searchItemRvAdapter
        }
    }
    private fun setListenerEvent() {
        //toolbar 뒤로가기 클릭
        binding.haymoonToolbar.setOnBackButtonClickListener {
            finish()
        }

        //검색 버튼 클릭
        binding.btnSearch.setOnClickListener {
            binding.editSearchArt.hideSoftKeyboard(this)
            searchViewModel.searchArtProductList(
                keyword = binding.editSearchArt.text.toString()
            )
        }

        //검색 결과 리스트 클릭
        searchItemRvAdapter.setItemClickListener(object : SearchItemRvAdapter.ItemClickListener {
            override fun onClickItem(item: SemaPsgudInfoKorInfoRowUiModel) {

            }
        })

        binding.editSearchArt.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    binding.btnSearch.performClick()
                    true
                }
                else -> false
            }
        }
    }

    private fun getDataFromVm(){

        //검색된 미술품 리스트 뿌려줌
        searchViewModel.searchArtProductList.observe(this){
            it.takeIf {it.isNotEmpty() }?.let { items ->
                binding.clNoSearchResult.isVisible = false
                searchItemRvAdapter.submitList(items)
            } ?: run {
                showNoSearchResultView()
            }
        }

        //에러 토스트 띄움
        searchViewModel.errorToast.observe(this,SingleEventObserver{
            showToast(it)
        })

        //클라이언트 핸들러 에러
        searchViewModel.clientHandleError.observe(this,SingleEventObserver{
           if(it == ClientHandleCodeType.NO_SEARCHED_DATA_VIEW_SHOWN){
               showNoSearchResultView()
           }
        })

        //로딩바
        searchViewModel.progress.observe(this){
            binding.pgSearchArt.isVisible = it
        }
    }

    //검색 결과 없음 뷰 보여줌
    private fun showNoSearchResultView(){
        binding.clNoSearchResult.visibility = View.VISIBLE
        binding.tvNoSearchResult.text = String.format(
            getString(R.string.no_search_result),
            "'" + binding.editSearchArt.text.toString() + "'"
        )
    }
}
