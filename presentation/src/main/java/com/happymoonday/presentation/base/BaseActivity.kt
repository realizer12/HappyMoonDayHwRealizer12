package com.happymoonday.presentation.base

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Create Date: 2024. 11. 24.
 *
 * 베아스가 되는 엑티비티 클래스
 *
 *
 * @author LeeDongHun
 *
 *
**/
abstract class BaseActivity<VDB : ViewDataBinding>(
    @LayoutRes val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //api 31이상일때만 splash screen 실행
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            preLoadSplashAScreen()
        }
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.onCreate()
    }
    abstract fun VDB.onCreate()

    //api 31이상일때만 splash screen 실행
    @RequiresApi(Build.VERSION_CODES.S)
    open fun preLoadSplashAScreen() = Unit

    protected fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
