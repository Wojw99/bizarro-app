package com.example.bizarro.ui.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bizarro.data.remote.responses.Opinion
import com.example.bizarro.repositories.RecordRepository
import com.example.bizarro.repositories.UserRepository
import com.example.bizarro.ui.AppState
import com.example.bizarro.util.models.FilterV
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val appState: AppState,
    private val repository: UserRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            val resource = repository.getUserProfile(repository.userId)
            Timber.d(resource.data.toString())



            val resource2 = repository.getUserOpinions(repository.userId)
            Timber.d(resource2.data.toString())

            // Dodawanie opinii dla innego użytkownika. Pierwsze pole "id" jest nieważne,
            // bo api i tak będzie je sobie samo ustawiało. Jeżeli operacja dodania
            // przebiegła pomyślnie, w odpowiedzi otrzymamy dodaną opinię. To niepotrzebne, ale
            // jakiś gość z YT mówił, że tak się tradycyjnie robi.
            val otherUserId = 1L
            val resource1 = repository.getUserProfile(otherUserId)
            Timber.d(resource1.data.toString())

            val resource33 = repository.getUserOpinions(otherUserId)
            Timber.d(resource33.data.toString())

            //val resource3 = repository.addUserOpinion(Opinion(-1, otherUserId, LocalDate.now(), 3, "Content"))
            //Timber.d(resource3.data.toString())
        }
    }
}