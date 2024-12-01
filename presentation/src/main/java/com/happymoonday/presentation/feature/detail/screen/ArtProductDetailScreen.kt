package com.happymoonday.presentation.feature.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happymoonday.design.compose.component.ArtProductImage
import com.happymoonday.design.compose.component.HayMoonToolbar
import com.happymoonday.design.compose.preview.HayMoonComponentPreview
import com.happymoonday.design.compose.preview.HayMoonScreenPreview
import com.happymoonday.design.compose.theme.HayMoonTheme
import com.happymoonday.presentation.model.SemaPsgudInfoKorInfoRowUiModel


/**
 * Create Date: 2024. 12. 1.
 *
 * Route로 wrapping 해서 screen 캡슐화
 *
 * @author LeeDongHun
 * 
 * 
**/
@Composable
fun ArtProductDetailRoute(
    semaPsgudInfoKorInfoRowUiModel: SemaPsgudInfoKorInfoRowUiModel,
    onBackBtnClicked: () -> Unit,
    onBookMarkClicked:(clickedArtProductInfo:SemaPsgudInfoKorInfoRowUiModel)->Unit,
){
    ArtProductDetailScreen(
        toolbarString = semaPsgudInfoKorInfoRowUiModel.productName,
        productInfo = semaPsgudInfoKorInfoRowUiModel,
        onBackBtnClicked = onBackBtnClicked,
        onBookMarkClicked = onBookMarkClicked
    )
}

/**
 * 상품 상세 화면 screen
 *
 * @param modifier Modifier
 * @param toolbarString 툴바 제목
 * @param productInfo 상품 정보
 * @param onBackBtnClicked 뒤로가기 버튼 클릭 시 이벤트
 *
 * @author LeeDongHun
 *
**/
@Composable
private fun ArtProductDetailScreen(
    modifier: Modifier = Modifier,
    toolbarString: String,
    productInfo: SemaPsgudInfoKorInfoRowUiModel,
    onBackBtnClicked: () -> Unit = {},
    onBookMarkClicked:(clickedArtProductInfo:SemaPsgudInfoKorInfoRowUiModel)->Unit = {}
){
    Scaffold(
        modifier = modifier
            .background(Color.White),
        topBar = {
            HayMoonToolbar(
                toolbarString = toolbarString,
                isBackBtnNeeded = true,
                isBookMarBtnNeeded = true,
                onBackBtnClicked = { onBackBtnClicked() },
                onBookMarkBtnClicked = { onBookMarkClicked(productInfo) }
            )
        }
    ) { contentPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ArtProductImage(
                productThumbnail = productInfo.mainImage,
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp, start = 20.dp),
                text = productInfo.productName,
                fontWeight = FontWeight(900),
                color = Color.Black,
                fontSize = 20.sp,
                maxLines = 1,
            )
            Text(
                modifier = Modifier
                    .padding(top = 3.dp, start = 20.dp),
                text = productInfo.productNameEn,
                fontWeight = FontWeight(900),
                color = Color.Black,
                fontSize = 13.sp,
                maxLines = 1,
            )
            productInfo.run {
                ArtProductDetailInfo(
                    title = "작가명",
                    content = writerName
                )
                ArtProductDetailInfo(
                    title = "제작년도",
                    content = dateOfMade
                )
                ArtProductDetailInfo(
                    title = "부문",
                    content = productCategory
                )
                ArtProductDetailInfo(
                    title = "규격",
                    content = productStandard
                )
                ArtProductDetailInfo(
                    title = "수집연도",
                    content = dateOfCollection
                )
                ArtProductDetailInfo(
                    title = "재료 및 기법",
                    content = materialTechInfo
                )
            }
        }
    }
}

/**
 * 미술품 상세 정보 구성  용 컴포넌트
**/
@Composable
private fun ArtProductDetailInfo(
   title:String,
   content:String
){
    Row(
        modifier = Modifier
            .heightIn(min = 30.dp)
            .background(Color.White)
            .padding(
                start = 20.dp,
                end = 20.dp
            ).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight(900),
            color = Color.Gray,
            fontSize = 13.sp,
            maxLines = 1,
        )
        Text(
            text = content,
            fontWeight = FontWeight(900),
            color = Color.Gray,
            fontSize = 13.sp,
            maxLines = 1,
        )
    }
}

/**
 * 미술품 상세 정보 구성  용 컴포넌트 프리뷰
**/
@HayMoonComponentPreview
@Composable
private fun ArtProductDetailInfoPreview(){
    HayMoonTheme {
        ArtProductDetailInfo(
            title = "작가명",
            content = "홍연화"
        )
    }
}

/**
 * 상품 상세 화면 screen  Preview
**/
@HayMoonScreenPreview
@Composable
fun ArtProductDetailScreenPreview() {
    val mockInfo = SemaPsgudInfoKorInfoRowUiModel(
        productCategory = "미술품",
        dateOfMade = "2021",
        dateOfCollection = "2021",
        productName = "꿈은 이루어진다.",
        productNameEn = "Dreams come true",
        writerName = "이동훈",
        productStandard = "100x100",
        materialTechInfo = "캔버스, 아크릴",
        mainImage = "https://picsum.photos/200/300",
        thumbNailImage = ""
    )
    HayMoonTheme {
        ArtProductDetailScreen(
            modifier = Modifier,
            toolbarString = "꿈은 이루어진다.",
            productInfo = mockInfo,
        )
    }
}
