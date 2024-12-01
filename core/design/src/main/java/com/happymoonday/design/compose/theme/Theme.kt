package com.happymoonday.design.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


/**
 * Create Date: 2024. 12. 1.
 *
 * 헤이문 ui theme 설정
 * @author LeeDongHun
 *
 *
**/
@Composable
fun HayMoonTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}
