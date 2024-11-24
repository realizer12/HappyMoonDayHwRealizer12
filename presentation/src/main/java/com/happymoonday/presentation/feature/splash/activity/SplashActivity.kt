package com.happymoonday.presentation.feature.splash.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.happymoonday.presentation.R
import com.happymoonday.presentation.base.BaseActivity
import com.happymoonday.presentation.databinding.ActivitySplashBinding
import com.happymoonday.presentation.feature.main.activity.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Create Date: 2024. 11. 24.
 *
 * splash activity
 * @author LeeDongHun
 *
 *
 **/
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun ActivitySplashBinding.onCreate() {
        initSet()
    }

    override fun preLoadSplashAScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition { true }//splash screeen theme로 계속 유지
        }
    }
    private fun initSet() {
        goToMainScreen()
    }
    //메인으로 가기
    private fun goToMainScreen(){
        lifecycleScope.launch {
            delay(1500L)//1500ms동안 splash screen 유지
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}


