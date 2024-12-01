package com.happymoonday.presentation.feature.favorite.fragment

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseFragment
import com.happymoonday.presentation.databinding.FragmentFavoriteBinding
import com.happymoonday.presentation.feature.detail.activity.ProductDetailActivity
import com.happymoonday.presentation.feature.favorite.viewmodel.FavoriteViewModel
import com.happymoonday.presentation.feature.search.activity.SearchActivity.Companion.CLICKED_ART_PRODUCT_INFO
import com.happymoonday.presentation.feature.search.adapter.SearchItemRvAdapter
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Create Date: 2024. 11. 24.
 *
 * 즐겨찾기 화면
 * @author LeeDongHun
 *
 *
 **/
@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private lateinit var favoriteAdapter: SearchItemRvAdapter
    private lateinit var startActivityResultLauncher: ActivityResultLauncher<Intent>


    override fun FragmentFavoriteBinding.onCreateView() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }

    private fun initSet() {
        favoriteAdapter = SearchItemRvAdapter()
        binding.rvFavorite.apply {
            adapter = favoriteAdapter
        }
    }

    private fun setListenerEvent() {
        //activity start result 받아옴.
        startActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                //즐겨찾기 완료이므로 즐겨찾기 탭으로 이동
                if(result.resultCode == ProductDetailActivity.BOOK_MARK_FINISH_RESULT_CODE){
                    favoriteViewModel.getBookMarkList()
                }
            }

        favoriteAdapter.setItemClickListener(object : SearchItemRvAdapter.ItemClickListener {
            override fun onClickItem(item: SemaPsgudInfoKorInfoRowUiModel) {
                startActivityResultLauncher.launch(Intent(activity?:return, ProductDetailActivity::class.java).apply {
                    putExtra(CLICKED_ART_PRODUCT_INFO, item)
                })
            }
        })
    }

    private fun getDataFromVm() {
        favoriteViewModel.bookMarkList.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(null)
            favoriteAdapter.submitList(it)
        }
    }
}
