package ca.tremblay95.billsplit.presentation.split_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.use_cases.GetSplitList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SplitListViewModel(
    getSplitList : GetSplitList
) : ViewModel() {
    val uiState : StateFlow<SplitListState> = getSplitList().map { result ->
        when (result) {
            is Result.Success<List<Split>> -> {
                SplitListState(splitList = result.data)
            }
            is Result.Error -> {
                SplitListState(error = result.message)
            }
            is Result.Loading -> {
                SplitListState(isLoading = true)
            }
            is Result.NotFound -> {
                SplitListState()
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = SplitListState(isLoading = true)
    )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

