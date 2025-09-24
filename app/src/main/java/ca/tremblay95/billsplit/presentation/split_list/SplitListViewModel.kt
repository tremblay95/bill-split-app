package ca.tremblay95.billsplit.presentation.split_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import ca.tremblay95.billsplit.data.mappers.toSplit
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SplitListViewModel(splitsRepository : SplitRepository) : ViewModel() {
    val uiState : StateFlow<SplitListState> = splitsRepository.getAllSplits().map { splitEntities ->
        SplitListState(splitEntities.map { it.toSplit() })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = SplitListState()
    )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

