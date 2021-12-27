package com.example.bizarro.ui.screens.user_profile.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    appState: AppState
): ViewModel()
{
    init {
        appState.bottomMenuVisible.value = false
    }

    val helpList = mutableStateOf<List<String>>(listOf(

        "zamienię - oznacza, że dany użytkownik chciałby się wymienić " +
                "produktem, którego umieścił ogłoszenie na inny produkt, o " +
                "którym informuje w szczegółach swojej oferty.",
        "kupię - ogłoszeniodawca poszukuje osób, które posiadają widoczny" +
                " na ogłoszeniu rower. Można skontaktować się z ogłoszeniodawcą w" +
                " celu przeprowadzenia transakcji.",
        "sprzedam - użytkownik chce sprzedać ogłoszony przez siebie produkt na konkretną cenę i szuka chętnych."
    ))

}