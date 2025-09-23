package ca.tremblay95.billsplit.ui.splitmethod.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tremblay95.billsplit.data.SplitsRepository
import ca.tremblay95.billsplit.ui.model.SplitMethodDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn

class SplitMethodDetailsViewModel(
    savedStateHandle : SavedStateHandle,
    private val splitsRepository : SplitsRepository
) : ViewModel() {
    val methodId : Int = checkNotNull(savedStateHandle[SplitMethodDetailsDestination.methodIdArg])
    val uiState : StateFlow<SplitMethodDetails> = splitsRepository.getSplitMethodDetails(methodId)
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = SplitMethodDetails()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
