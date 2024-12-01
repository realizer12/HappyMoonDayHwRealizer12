package com.happymoonday.design.compose.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.happymoonday.design.compose.preview.HayMoonComponentPreview

/**
 * Create Date: 2024. 12. 1.
 *
 * 작품 이미지를 url 주소를 받아 보여주는 컴포넌트
 * 추후, 이미지 공통처리 가능하기에 design모듈로 옮김.
 *
 * @author LeeDongHun
 *
 *
 **/
@Composable
fun ArtProductImage(
    productThumbnail: String,
    imageRatio: ArtProductImageRatioType = ArtProductImageRatioType.ART_PRODUCT_DETAIL_RATIO
) {
    AsyncImage(
        modifier = Modifier
            .aspectRatio(imageRatio.ratio),
        contentScale = ContentScale.Crop,
        model = productThumbnail,
        contentDescription = "작품 썸네일 이미지",
    )
}

/**
 * 작품 이미지 비율 타입
 */
enum class ArtProductImageRatioType(val ratio: Float) {
    ART_PRODUCT_DETAIL_RATIO(1f)
}

/**
 * ArtProductImage 프리뷰 함수
**/
@HayMoonComponentPreview
@Composable
private fun ArtProductImagePreview() {
    ArtProductImage(
        productThumbnail = "https://picsum.photos/200/300",
        imageRatio = ArtProductImageRatioType.ART_PRODUCT_DETAIL_RATIO,
    )
}
