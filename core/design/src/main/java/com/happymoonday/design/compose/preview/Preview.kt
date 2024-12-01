package com.happymoonday.design.compose.preview

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview


/**
 * 컴포넌트 용 프리뷰
 **/
@Preview(
    name = "HayMoonComponentPreview"
)
annotation class HayMoonComponentPreview


/**
 * screen 용 프리뷰
 **/
@Preview(
    name = "HayMoonScreenPreview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFFL,
    device = Devices.NEXUS_5X
)
annotation class HayMoonScreenPreview
