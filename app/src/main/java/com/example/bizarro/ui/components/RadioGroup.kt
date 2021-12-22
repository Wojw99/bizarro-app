package com.example.bizarro.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bizarro.ui.theme.kBlack
import com.example.bizarro.ui.theme.kBlueDark
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Dimens

@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    labels: List<String>,
    selectedLabel: MutableState<String>,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        for(label in labels) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Dimens.cornerRadius))
                    .clickable { selectedLabel.value = label }
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = Dimens.standardPadding,
                        vertical = 7.dp,
                    )
                ) {
                    // * * * RADIO BUTTON * * *
                    RadioButton(
                        selected = selectedLabel.value == label,
                        colors = RadioButtonDefaults.colors(kBlueDark),
                        onClick = { selectedLabel.value = label }
                    )

                    Spacer(modifier = Modifier.width(Dimens.standardPadding))

                    // * * * TEXT * * *
                    Text(
                        text = CommonMethods.formatRecordTypeText(label),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = kBlack,
                        ),
                    )
                }
            }
        }
    }
}