package ca.tremblay95.billsplit.ui.split

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.tremblay95.billsplit.data.SplitsRepository

class SplitMethodDetailsViewModel(
    savedStateHandle : SavedStateHandle,
    private val splitsRepository : SplitsRepository
) : ViewModel() {
    val methodId : Int = checkNotNull(savedStateHandle[SplitMethodDetailsDestination.methodIdArg])
}
