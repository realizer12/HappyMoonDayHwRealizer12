package com.happymoonday.presentation.feature.main.activity

import android.os.Bundle
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

    override fun ActivityMainBinding.onCreate() {
        initSet()
    }
    private fun initSet() {

    }
}
