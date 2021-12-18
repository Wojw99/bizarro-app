package com.example.bizarro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bizarro.ui.theme.kBlack
import com.example.bizarro.ui.theme.kBlueDark
import com.example.bizarro.ui.theme.kGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.util.Dimens

@ExperimentalComposeUiApi
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    hint: String = "",
    initialText: String = "",
    onTextChanged: (String) -> Unit = {},
) {
    var text by remember {
        mutableStateOf(initialText)
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "" && initialText == "")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val padding = 12.dp

    Box (modifier = modifier) {
        BasicTextField(
            modifier = Modifier
                .border(1.5.dp, kBlueDark, RoundedCornerShape(Dimens.cornerRadius))
                .background(kWhite, RoundedCornerShape(Dimens.cornerRadius))
                .padding(padding),
            value = text,
            onValueChange = {
                text = it
                isHintDisplayed = it.isEmpty()
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = kBlack, fontSize = 16.sp),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
                onTextChanged(text)
            }),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
        if (isHintDisplayed) {
            Text(
                color = kGray, text = hint,
                modifier = modifier.align(alignment = Alignment.CenterStart).padding(padding)
            )
        }
    }
}