package com.example.bizarro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.TopBarAction

@Composable
fun TopBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String = "",
    actions: List<TopBarAction> = listOf(),
    showBackButton: Boolean = true,
    onTitleClick: () -> Unit = {},
    backgroundColor: Color = Color.Transparent,
) {
    Row(
        modifier = modifier
            .background(backgroundColor)
            .height(Dimens.topBarHeight),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        if (showBackButton) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = Strings.iconBack,
                    modifier = Modifier
                        .height(27.dp)
                        .width(27.dp),
                    tint = colors.onSurface
                )
            }
        } else {
            Spacer(modifier = Modifier.width(5.dp))
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = title,
            style = TextStyle(
                //color = colors.onSurface,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.clickable { onTitleClick() },
            color = colors.onSurface
        )

        Spacer(modifier = Modifier.width(Dimens.standardPadding))

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (action in actions) {
                IconButton(
                    onClick = action.onClick
                ) {
                    Icon(
                        action.icon,
                        contentDescription = action.contentDescription,
                        modifier = Modifier
                            .height(27.dp)
                            .width(27.dp),
                        tint = colors.onSurface
                    )
                }
            }
        }
    }
}