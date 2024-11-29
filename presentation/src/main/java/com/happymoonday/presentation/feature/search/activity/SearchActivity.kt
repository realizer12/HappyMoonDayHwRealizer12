package com.happymoonday.presentation.feature.search.activity

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isVisible
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
class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {

    private lateinit var searchItemRvAdapter: SearchItemRvAdapter
    private val searchViewModel: SearchViewModel by viewModels()

    override fun ActivitySearchBinding.onCreate() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }

    private fun initSet() {
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
            checkSearchKeywordInputOrNull() ?: return@setOnClickListener
            binding.editSearchArt.hideSoftKeyboard(this)

            //검색어가 있는 경우, 미술품 검색 리스트 조회
            searchViewModel.searchArtProductList(
                keyword = binding.editSearchArt.text.toString()
            )
        }

        //검색 결과 리스트 클릭
        searchItemRvAdapter.setItemClickListener(object : SearchItemRvAdapter.ItemClickListener {
            override fun onClickItem(item: SemaPsgudInfoKorInfoRowUiModel) {
                showToast(item.productName)
            }
        })

        //키보드 검색 버튼 클릭 처리
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

    private fun getDataFromVm() {
        //검색된 미술품 리스트 뿌려줌
        searchViewModel.searchArtProductList.observe(this) {
            it.takeIf { it.isNotEmpty() }?.let { items ->
                binding.clNoSearchResult.isVisible = false
                searchItemRvAdapter.submitList(items)
            } ?: run {//검색결과 없는 경우 검색결과 없음 보여주고 리스트 초기화
                showNoSearchResultView()
                searchItemRvAdapter.submitList(null)
            }
        }

        //에러 토스트 띄움
        searchViewModel.errorToast.observe(this, SingleEventObserver {
            showToast(it)
        })

        //로딩바
        searchViewModel.progress.observe(this) {
            binding.pgSearchArt.isVisible = it
        }
    }

    //검색어 입력 안했을 여부 체크 -> 입력 안 했으면 null 반환
    //검색어 입력 안했을 경우 토스트 메세지 보여줌
    private fun checkSearchKeywordInputOrNull(): Unit? {
        if (binding.editSearchArt.text.toString().isEmpty()) {
            showToast(getString(R.string.search_hint))
            return null
        }
        return Unit
    }

    //검색 결과 없음 뷰 보여줌
    private fun showNoSearchResultView() {
        binding.clNoSearchResult.visibility = View.VISIBLE
        binding.tvNoSearchResult.text = String.format(
            getString(R.string.no_search_result),
            "'" + binding.editSearchArt.text.toString() + "'"
        )
    }
}
