package com.happymoonday.presentation.feature.main.activity

import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseActivity
import com.happymoonday.presentation.databinding.ActivityMainBinding
import com.happymoonday.presentation.feature.main.viewmodel.MainViewModel
import com.happymoonday.presentation.util.SingleEventObserver
import dagger.hilt.android.AndroidEntryPoint

/**
 * Create Date: 2024. 11. 24.
 *
 * 메인 엑티비티
 * @author LeeDongHun
 *
 *
 **/
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel:MainViewModel by viewModels()

    //네비게이션 컨트롤러
    private lateinit var navController: NavController

    override fun ActivityMainBinding.onCreate() {
        initSet()
        setListenerEvent()
        getDataFromVm()
    }
    private fun initSet() {
        setBottomNavigation()
    }
    private fun setListenerEvent(){
        // 뒤로가기 콜백 등록
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                when (navController.currentDestination?.id) {
                    R.id.main_seoul_art_home -> {
                        // 홈 화면에서는 앱 종료
                        finish()
                    }
                    else -> {
                        // 다른 탭에서는 홈으로 이동
                        navController.navigate(R.id.main_seoul_art_home, null, NavOptions.Builder()
                            .setPopUpTo(R.id.main_seoul_art_home, false)
                            .setLaunchSingleTop(true)
                            .build()
                        )
                    }
                }
            }
        })
    }

    private fun getDataFromVm(){
        //즐겨찾기 탭으로 이동
        mainViewModel.moveToFavoriteFragment.observe(this,SingleEventObserver{
            binding.bnMenu.selectedItemId = R.id.main_seoul_art_favorite
        })
    }

    //바텀 메뉴 세팅
    private fun setBottomNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()//네비게시션 컨트롤러
        binding.bnMenu.setupWithNavController(navController)//바텀 네비게이션에  컨트롤러 연결
    }
}
