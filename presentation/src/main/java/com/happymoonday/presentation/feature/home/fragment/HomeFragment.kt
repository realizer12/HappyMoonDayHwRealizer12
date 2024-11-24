package com.happymoonday.presentation.feature.home.fragment

import android.content.Intent
import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseFragment
import com.happymoonday.presentation.databinding.FragmentHomeBinding
import com.happymoonday.presentation.feature.search.activity.SearchActivity

/**
 * Create Date: 2024. 11. 24.
 *
 * 홈 화면
 * @author LeeDongHun
 *
 **/
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun FragmentHomeBinding.onCreateView() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }

    private fun initSet() {
    }

    private fun setListenerEvent() {
        // 검색 화면으로 이동
        binding.btnGotoSearchView.setOnClickListener {
            goToSearchView()
        }
    }

    //검색화면 이동
    private fun goToSearchView() {
        activity?.let { startActivity(Intent(it, SearchActivity::class.java)) }
    }

    private fun getDataFromVm() {
    }
}
