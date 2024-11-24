package com.happymoonday.presentation.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

//키보드 숨기기
fun View.hideSoftKeyboard(context: Context) {
    val imm = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    if (this.windowToken != null) {
        //혹시나 show force가 되어있는 Keyboard는 hide 영향을 안받게 HIDE_NOT_ALWAys를 적용
        imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
    this.requestFocus()
}
