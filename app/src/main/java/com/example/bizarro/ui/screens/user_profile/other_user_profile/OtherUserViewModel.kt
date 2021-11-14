package com.example.bizarro.ui.screens.user_profile.other_user_profile

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtherUserViewModel @Inject constructor(
   val appState: AppState
): ViewModel()
{

    val opinionOtherUserList = mutableStateListOf<String>()

    val opinionsOtherUser= mutableStateOf(listOf(
        "User1: 5, super produkty",
        "User2: 4, dobre produkty",
        "User3: 3, pozytywne produkty",
        "User4: 5, super produkty",
        "User5: 5, super produkty",
        "User6: 2, takie średnie produkty"
        ),

        )


    init {
        appState.bottomMenuVisible.value = false

    }

    fun addOpinion(opinionText: String){

         opinionOtherUserList.add(opinionText)

    }

}
