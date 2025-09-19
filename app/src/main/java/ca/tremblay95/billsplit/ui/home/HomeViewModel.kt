package ca.tremblay95.billsplit.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tremblay95.billsplit.data.SplitsRepository
import ca.tremblay95.billsplit.data.models.SplitMethod
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(splitsRepository : SplitsRepository) : ViewModel() {
// TODO: retrieve all splits from Room database
    val homeUiState : StateFlow<HomeUiState> = splitsRepository.getAllSplitMethods().map {
        HomeUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeUiState()
    )
}

data class HomeUiState(val splitList : List<SplitMethod> = listOf())
