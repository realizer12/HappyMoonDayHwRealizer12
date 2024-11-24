package com.happymoonday.presentation.feature.favorite.fragment

import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseFragment
import com.happymoonday.presentation.databinding.FragmentFavoriteBinding

/**
 * Create Date: 2024. 11. 24.
 *
 * 즐겨찾기 화면
 * @author LeeDongHun
 *
 *
 **/
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    override fun FragmentFavoriteBinding.onCreateView() {
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
