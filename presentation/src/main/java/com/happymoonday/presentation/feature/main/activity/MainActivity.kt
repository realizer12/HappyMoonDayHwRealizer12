package com.happymoonday.presentation.feature.main.activity

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseActivity
import com.happymoonday.presentation.databinding.ActivityMainBinding
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

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (navController.currentDestination?.id != R.id.main_seoul_art_home) {
            navController.navigate(R.id.main_seoul_art_home)
        } else {
            super.onBackPressed()
        }
    }

    private fun getDataFromVm(){

    }
    //바텀 메뉴 세팅
    private fun setBottomNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()//네비게시션 컨트롤러
        binding.bnMenu.setupWithNavController(navController)//바텀 네비게이션에  컨트롤러 연결
    }
}
