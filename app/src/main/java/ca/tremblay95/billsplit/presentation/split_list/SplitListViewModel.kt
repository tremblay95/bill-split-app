package ca.tremblay95.billsplit.presentation.split_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tremblay95.billsplit.domain.use_cases.GetSplitList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SplitListViewModel(
    getSplitList : GetSplitList
) : ViewModel() {
    val uiState : StateFlow<SplitListState> = getSplitList().map { splitList ->
        SplitListState(splitList)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = SplitListState()
    )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

