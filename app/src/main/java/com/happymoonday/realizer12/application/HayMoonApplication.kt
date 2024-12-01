package com.happymoonday.realizer12.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

/**
 * Create Date: 2024. 11. 23.
 *
 *
 * 해이문 안드로이드 과제 앱
 * application class 이다.
 *
 * @author LeeDongHun
 *
 *
**/
@HiltAndroidApp
class HayMoonApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)//다크모드 비활성화
    }
}
