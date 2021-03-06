package com.example.bizarro.ui.screens.user_profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Strings

@Composable
fun AboutAppScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = false

    BizarroTheme(
        darkTheme = viewModel.appState.isDarkTheme.value
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            TopBar(
                navController = navController,
                title = Strings.empty,

                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                "Informacje o aplikacji",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface
            )


            Spacer(modifier = Modifier.height(50.dp))

            Text(
                "Aplikacja Bizarro zosta??a stworzona w celu zamieszczania og??osze?? " +
                        "dotycz??cych: kupna, sprzeda??y, zamiany, czy wypo??yczenia produktu, " +
                        "jakimi s?? rowery.\n \n" +
                        " Bizarro zawiera w sobie funkcj?? por??wnania " +
                        "wybranych przez u??ytkownika og??osze??, jak r??wnie?? kontakt spo??eczny " +
                        "pomi??dzy uzytkownikami, za pomoc?? podanych danych kontaktowych i " +
                        "mo??liwo??ci przesy??ania swoich opinii w wyznaczonym na to miejscu.",


                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                "Tw??rcy Bizarro: ",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Icon(
                        Icons.Default.Person,
                        "Icon description",
                        tint = MaterialTheme.colors.onSurface
                    )
                    Text("Adam Wojtala", color = MaterialTheme.colors.onSurface)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Icon(
                        Icons.Default.Person,
                        "Icon description",
                        tint = MaterialTheme.colors.onSurface
                    )
                    Text("Wojciech Wojtasek", color = MaterialTheme.colors.onSurface)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Icon(
                        Icons.Default.Person,
                        "Icon description",
                        tint = MaterialTheme.colors.onSurface
                    )
                    Text("Konrad Matuszewski", color = MaterialTheme.colors.onSurface)
                }
            }
        }
    }

}

