package ca.tremblay95.billsplit.presentation.split_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SplitDetailsViewModel(
    savedStateHandle : SavedStateHandle,
//    private val splitsRepository : SplitsRepository
) : ViewModel() {
    val methodId : Int = checkNotNull(savedStateHandle[SplitMethodDetailsDestination.methodIdArg])
//    val uiState : StateFlow<SplitMethodDetails> = splitsRepository.getSplitMethodDetails(methodId)
//        .filterNotNull()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//            initialValue = SplitMethodDetails()
//        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
