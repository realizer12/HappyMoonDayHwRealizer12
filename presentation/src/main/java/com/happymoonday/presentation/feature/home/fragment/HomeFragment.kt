package com.happymoonday.presentation.feature.home.fragment

import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseFragment
import com.happymoonday.presentation.databinding.FragmentHomeBinding

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
    }

    private fun getDataFromVm() {
    }
}
