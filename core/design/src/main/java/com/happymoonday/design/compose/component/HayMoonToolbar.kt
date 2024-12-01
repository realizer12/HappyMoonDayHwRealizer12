package com.happymoonday.design.compose.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happymoonday.design.R
import com.happymoonday.design.compose.preview.HayMoonComponentPreview
import com.happymoonday.design.compose.theme.HayMoonTheme


/**
 * Create Date: 2024. 12. 1.
 *
 *
 * 헤이문 과제앱에서 공통적으로 사용가능한 툴바
 *
 * @param modifier 툴바의 Modifier
 * @param toolbarString 툴바의 제목
 * @param isBackBtnNeeded 뒤로가기 버튼이 필요한지 여부  true면 보여줌
 * @param onBackBtnClicked 뒤로가기 버튼 클릭 시 이벤트
 * @param isBookMarBtnNeeded 북마크 버튼이 필요한지 여부 true면 보여줌
 * @param onBookMarkBtnClicked 북마크 버튼 클릭 시 이벤트
 *
 * @author LeeDongHun
 *
 *
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HayMoonToolbar(
    modifier: Modifier = Modifier,
    toolbarString: String,
    isBackBtnNeeded: Boolean = false,
    onBackBtnClicked: () -> Unit = {},
    isBookMarBtnNeeded: Boolean = false,
    isBookMarked: Boolean = false,
    onBookMarkBtnClicked: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors =  TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Text(
                text = toolbarString,
                fontWeight = FontWeight(700),
                color = Color.Black,
                fontSize = 16.sp,
                maxLines = 1,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onBackBtnClicked() }
            ) {
                if (isBackBtnNeeded) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_back_arrow),
                        contentDescription = "뒤로가기 버튼"
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = { onBookMarkBtnClicked() }
            ) {
                if (isBookMarBtnNeeded) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = if (isBookMarked) {//북마크 되어있는지 여부
                            painterResource(id = R.drawable.icon_marked_star)
                        } else {
                            painterResource(id = R.drawable.icon_un_marked_star)
                        },
                        contentDescription = "북마크 버튼"
                    )
                }
            }
        }
    )
}

/**
 * HayMoonToolbar 프리뷰 함수
 **/
@HayMoonComponentPreview
@Composable
fun HayMoonToolbarPreview() {
    HayMoonTheme {
        HayMoonToolbar(
            toolbarString = "꿈은 이루어진다.",
            isBackBtnNeeded = true,
            isBookMarBtnNeeded = true,
            isBookMarked = false
        )
    }
}
