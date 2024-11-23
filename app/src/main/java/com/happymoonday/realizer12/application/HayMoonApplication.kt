package com.happymoonday.realizer12.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

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
class HayMoonApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
