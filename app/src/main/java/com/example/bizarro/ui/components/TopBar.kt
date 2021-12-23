package com.example.bizarro.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
) {
    Row(
        modifier = modifier
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
                        .width(27.dp)
                )
            }
        } else {
            Spacer(modifier = Modifier.width(5.dp))
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = title,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
            modifier = Modifier.clickable { onTitleClick() },
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
                            .width(27.dp)
                    )
                }
            }
        }
    }
}