package ca.tremblay95.billsplit.presentation.create_split

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ca.tremblay95.billsplit.data.SplitsRepository
import ca.tremblay95.billsplit.data.mappers.toSplitEntity
import ca.tremblay95.billsplit.domain.model.Split

// TODO: Pass in GetMethodsListUseCase
class CreateSplitViewModel(
    private val splitsRepository : SplitsRepository
) : ViewModel() {
    var uiState by mutableStateOf(CreateSplitState())
        private set

    fun updateUiState(methodDetails : Split) {
        uiState = CreateSplitState(
            split = methodDetails,
            isEntryValid = validateInput(methodDetails)
        )
    }

    suspend fun saveSplitMethod() {
        if (validateInput()) {
            splitsRepository.insertSplit(uiState.split.toSplitEntity())
        }
    }

    private fun validateInput(state : Split = uiState.split) : Boolean {
        return with(state) {
            name.isNotBlank() // do we need to ensure there's always a description?
        }
    }
}

