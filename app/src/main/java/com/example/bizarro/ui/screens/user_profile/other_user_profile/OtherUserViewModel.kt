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
    //val recordList = mutableStateOf<List<Record>>(listOf())
    //val opinionOtherUserList = mutableStateOf<List<OpinionOfOtherUser>>(listOf())

    init {
        appState.bottomMenuVisible.value = false

    }

    fun addOpinion(opinionText: String){

         opinionOtherUserList.add(opinionText)

    }





}

//data class OpinionOfOtherUser
//(
//    val id: Int,
//    val opinionMark: String,
//    val opinionText: String
//
//    )