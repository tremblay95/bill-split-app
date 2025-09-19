package ca.tremblay95.billsplit.ui.split

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ca.tremblay95.billsplit.data.SplitsRepository
import ca.tremblay95.billsplit.ui.model.SplitMethod
import ca.tremblay95.billsplit.ui.model.toSplitMethod

// TODO: pass in split repository
class NewSplitMethodViewModel(
    private val splitsRepository : SplitsRepository
) : ViewModel() {
    var methodUiState by mutableStateOf(MethodUiState())
        private set

    fun updateUiState(methodDetails : SplitMethod) {
        methodUiState = MethodUiState(
            methodDetails = methodDetails,
            isEntryValid = validateInput(methodDetails)
        )
    }

    suspend fun saveSplitMethod() {
        if (validateInput()) {
            splitsRepository.insertSplitMethod(methodUiState.methodDetails.toSplitMethod())
        }
    }

    private fun validateInput(uiState : SplitMethod = methodUiState.methodDetails) : Boolean {
        return with(uiState) {
            name.isNotBlank() // do we need to ensure there's always a description?
        }
    }
}

data class MethodUiState(
    val methodDetails : SplitMethod = SplitMethod(),
    val isEntryValid : Boolean = false
)
