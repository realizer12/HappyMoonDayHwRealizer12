package com.happymoonday.design.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.happymoonday.design.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Create Date: 2024. 11. 25.
 *
 * haymoon toolbar custom view
 *
 * @author LeeDongHun
 *
 *
**/
class HayMoonToolbar(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var toolbarText: String = ""
    private var toolbarTextVisibility: Boolean = false
    private var backBtnVisibility: Boolean = false

    init {
        initAttrs(attrs)
        initView()
    }

    private fun initAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HayMoonToolbar,
            0, 0
        ).apply {
            // 속성으로 전달받은 값을 대입하는 부분
            try {
                //툴바 text 적용 및 visibility
                toolbarText = getString(R.styleable.HayMoonToolbar_toolbarText) ?: ""
                toolbarTextVisibility = getBoolean(R.styleable.HayMoonToolbar_toolbarTextVisibility,false)

                //toolbar menu별 visibility
                backBtnVisibility = getBoolean(R.styleable.HayMoonToolbar_toolbarBackBtnVisibility,false)
            } finally {
                recycle()
            }
        }
    }

    private fun setViewVisibleWithBoolean(visible: Boolean): Int {
        return if (visible) View.VISIBLE else View.INVISIBLE
    }

    //뒤로가기 버튼 클릭시
    fun setOnBackButtonClickListener(action: (view: View) -> Unit) {
        findViewById<AppCompatImageView>(R.id.iv_toolbar_back).setOnClickListener {
            findViewTreeLifecycleOwner()?.lifecycleScope?.let {
                it.launch {
                    hideKeyboard()
                    delay(100L)//좀더 자연스럽게 뒤로가기 처리 위하여 100 delay줌.
                    action(this@HayMoonToolbar)
                }
            }?:run {
                action(this)
            }
        }
    }

    //키보드 숨기기 -뒤로가기시 사용
    private fun hideKeyboard() {
        val imm = context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (this@HayMoonToolbar.windowToken != null) {
            //혹시나 show force가 되어있는 Keyboard는 hide 영향을 안받게 HIDE_NOT_ALWAys를 적용
            imm.hideSoftInputFromWindow(this@HayMoonToolbar.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
        this@HayMoonToolbar.requestFocus()
    }

    private fun initView() {
        inflate(context, R.layout.haymoon_toolbar, this)
        findViewById<AppCompatTextView>(R.id.tv_left_title_toolbar).apply {
            text = toolbarText
            visibility = setViewVisibleWithBoolean(toolbarTextVisibility)
        }
        findViewById<AppCompatImageView>(R.id.iv_toolbar_back).visibility =
            setViewVisibleWithBoolean(backBtnVisibility)
    }
}
