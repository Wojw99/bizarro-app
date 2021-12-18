package com.example.bizarro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bizarro.ui.theme.kBlueDark
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.Dimens

@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    text: String,
    iconVector: ImageVector,
    contentDescription: String,
    onButtonClick: () -> Unit = {},
    reverseColors: Boolean = false,
) {
    val backColor = if (reverseColors) kWhite else kBlueDark
    val contentColor = if (reverseColors) kBlueDark else kWhite
    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(Dimens.cornerRadius))
            .clip(RoundedCornerShape(Dimens.cornerRadius))
            .background(backColor)
            .height(Dimens.barHeight)
            .clickable { onButtonClick() }
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center).padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if(text.isNotEmpty()) {
                Text(
                    text,
                    style = TextStyle(color = contentColor, fontSize = 16.sp),
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Icon(
                iconVector,
                contentDescription,
                tint = contentColor,
            )
        }
    }
}
