package com.happymoonday.presentation.feature.home.fragment

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseFragment
import com.happymoonday.presentation.databinding.FragmentHomeBinding
import com.happymoonday.presentation.feature.detail.activity.ProductDetailActivity
import com.happymoonday.presentation.feature.main.viewmodel.MainViewModel
import com.happymoonday.presentation.feature.search.activity.SearchActivity

/**
 * Create Date: 2024. 11. 24.
 *
 * 홈 화면
 * @author LeeDongHun
 *
 **/
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var startActivityResultLauncher: ActivityResultLauncher<Intent>
    private val mainViewModel:MainViewModel by activityViewModels()
    override fun FragmentHomeBinding.onCreateView() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }

    private fun initSet() {
    }

    private fun setListenerEvent() {

        //activity start result 받아옴.
        startActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                //즐겨찾기 완료이므로 즐겨찾기 탭으로 이동
                if(result.resultCode == ProductDetailActivity.BOOK_MARK_FINISH_RESULT_CODE){
                    mainViewModel.moveToFavoriteFragment()
                }
            }

        // 검색 화면으로 이동
        binding.btnGotoSearchView.setOnClickListener {
            goToSearchView()
        }
    }

    //검색화면 이동
    private fun goToSearchView() {
        activity?.let {
            startActivityResultLauncher.launch(Intent(it, SearchActivity::class.java))
        }
    }
    private fun getDataFromVm() {
    }
}
