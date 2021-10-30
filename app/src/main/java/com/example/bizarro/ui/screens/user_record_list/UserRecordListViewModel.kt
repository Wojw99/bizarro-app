package com.example.bizarro.ui.screens.user_record_list

import androidx.lifecycle.ViewModel
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.ui.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserRecordListViewModel @Inject constructor(
    appState: AppState,
    repository: RecordRepository,
) : ViewModel() {
    init {
        appState.bottomMenuVisible.value = true
    }
}