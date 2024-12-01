package com.happymoonday.design.compose.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.happymoonday.design.compose.preview.HayMoonComponentPreview

/**
 * Create Date: 2024. 12. 1.
 *
 * Description 헤이문 과제앱 다이얼로그 Compose 컴포넌트
 *
 * @author LeeDongHun
 *
 *
 **/
@Composable
fun HayMoonDialog(
    modifier: Modifier = Modifier,
    message: String,
    confirmButtonText: String? = null,
    dismissButtonText: String? = null,
    messageTextColor: Color = Color.Black,
    confirmTextColor: Color = Color.Black,
    dismissTextColor: Color = Color.Black,
    onConfirmClicked: () -> Unit,
    onDismissClicked: () -> Unit,
    onDismissRequest: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        content = {
            Surface(
                modifier = modifier,
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier.padding(top = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = message,
                            fontSize = 17.sp,
                            color = messageTextColor,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        if (dismissButtonText != null) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 20.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }) {
                                        onDismissClicked()
                                    },
                                text = dismissButtonText,
                                color = dismissTextColor,
                                textAlign = TextAlign.Center
                            )
                        }
                        if (confirmButtonText != null) {
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 20.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }) {
                                        onConfirmClicked()
                                    },
                                text = confirmButtonText,
                                color = confirmTextColor,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
@HayMoonComponentPreview
fun HayMoonDialogPreview() {
    HayMoonDialog(
        message = "메시지",
        confirmButtonText = "확인",
        dismissButtonText = "취소",
        onDismissRequest = {},
        onConfirmClicked = {},
        onDismissClicked = {}
    )
}
