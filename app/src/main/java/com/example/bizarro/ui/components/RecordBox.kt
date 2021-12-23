package com.example.bizarro.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.api.models.Record
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.record_details.RecordDetailsViewModel
import com.example.bizarro.ui.screens.search.TypeSpecificTitle
import com.example.bizarro.ui.theme.kBlack
import com.example.bizarro.ui.theme.kGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings

@Composable
fun RecordBox(
    record: Record,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(Dimens.cornerRadius))
            .clip(RoundedCornerShape(Dimens.cornerRadius))
            .background(colors.surface)
            .height(150.dp)
            .clickable { onClick() }
    ) {
        Row {
            // * * * * * * * * IMAGE BOX * * * * * * * *
            Box(modifier = Modifier.weight(12f), contentAlignment = Alignment.Center) {
                val painter = rememberImagePainter(
                    record.imagePath ?: Constants.RECORD_DEFAULT_IMG_URL
                )

                Image(
                    painter = painter,
                    contentDescription = Strings.recordImage,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )

                if (painter.state is ImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }
            }

            // * * * * * * * * CONTENT BOX * * * * * * * *
            Box(
                modifier = Modifier
                    .weight(11f)
                    .padding(10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // * * * * * * * * NAME * * * * * * * *
                    Text(
                        text = record.name,
                        style = TextStyle(
                            fontSize = countTextSizeForName(record.name),
                            fontWeight = FontWeight.Normal,
                            color = colors.onSurface,
                        )
                    )

                    // * * * * * * * * TYPE SPECIFIC TITLE * * * * * * * *
                    TypeSpecificTitle(record = record)

                    // * * * * * * * * ADDRESS, DATE * * * * * * * *
                    Text(
                        text = "${record.address.city}, ${CommonMethods.convertToRecordBoxDateFormat(record.creationDate)}",
                        style = TextStyle(fontSize = 12.sp, color = colors.onSurface)
                    )
                }
            }
        }
    }
}


private fun countTextSizeForName(text: String): TextUnit {
    return if (text.length < 30) 16.sp
    else 13.sp
}