package com.happymoonday.presentation.util

import android.os.SystemClock
import android.view.View

/**
 * Create Date: 2024. 12. 1.
 *
 * 한번 클릭시 여러번 클릭 방지를 위한 리스너
 * 1초 interval로 설정
 * @author LeeDongHun
 *
 *
**/
private class OnSingleClickListener(
    private var interval: Int = 1000,
    private var onSingleClick: (View) -> Unit
) : View.OnClickListener {
    private var lastClickTime: Long = 0
    override fun onClick(v: View) {
        val elapsedRealtime = SystemClock.elapsedRealtime()
        if ((elapsedRealtime - lastClickTime) < interval) return
        lastClickTime = elapsedRealtime
        onSingleClick(v)
    }
}

fun View.setOnSingleClickListener(onSingleClick: View.OnClickListener) {
    val oneClick = OnSingleClickListener {
        onSingleClick.onClick(it)
    }
    setOnClickListener(oneClick)
}
