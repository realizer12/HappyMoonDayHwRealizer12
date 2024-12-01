package com.happymoonday.presentation.util

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 키보드 숨기기 기능
 **/
fun View.hideSoftKeyboard(context: Context) {
    val imm = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    if (this.windowToken != null) {
        //혹시나 show force가 되어있는 Keyboard는 hide 영향을 안받게 HIDE_NOT_ALWAys를 적용
        imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
    this.requestFocus()
}

/**
 * recyclerView 스크롤 마지막까지 했는지 체크후
 * 마지막까지 스크롤 했을 경우 onLoadMore 호출
 *
 * @param layoutManager LinearLayoutManager
 * @param onLoadMore 다음 페이징이 가능할때 호출할 함수
 **/
fun RecyclerView.setOnPagingListener(
    layoutManager: LinearLayoutManager,
    onLoadMore: () -> Unit
) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            // dy>0 아래로 스크롤중 더이상 스크롤 할수 없는 경우 (맨 마지막까지 스크롤한 경우)
            if (dy > 0 && !recyclerView.canScrollVertically(1)) {
                val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                val adapter = recyclerView.adapter
                if (adapter != null && lastVisiblePosition == adapter.itemCount - 1) {
                    onLoadMore()
                }
            }
        }
    })
}


fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics)
