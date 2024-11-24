package com.happymoonday.presentation.feature.search.activity

import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseActivity
import com.happymoonday.presentation.databinding.ActivitySearchBinding

/**
 * Create Date: 2024. 11. 24.
 *
 * 검색 화면
 * @author LeeDongHun
 *
 *
**/
class SearchActivity:BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    override fun ActivitySearchBinding.onCreate() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }
    private fun initSet(){

    }
    private fun setListenerEvent() {
        //toolbar 뒤로가기 클릭
        binding.haymoonToolbar.setOnBackButtonClickListener {
            finish()
        }
    }
    private fun getDataFromVm(){

    }
}
