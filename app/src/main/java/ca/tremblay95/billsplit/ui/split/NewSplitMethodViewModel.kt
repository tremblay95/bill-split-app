package ca.tremblay95.billsplit.ui.split

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ca.tremblay95.billsplit.ui.model.SplitMethodDetails

// TODO: pass in split repository
class NewSplitMethodViewModel : ViewModel() {
    var methodUiState by mutableStateOf(MethodUiState())
        private set

    fun updateUiState(methodDetails : SplitMethodDetails) {
        methodUiState = MethodUiState(
            methodDetails = methodDetails,
            isEntryValid = validateInput(methodDetails)
        )
    }

    suspend fun saveSplitMethod() {
        if (validateInput()) {
            // TODO: make call to split repository
        }
    }

    private fun validateInput(uiState : SplitMethodDetails = methodUiState.methodDetails) : Boolean {
        return with(uiState) {
            name.isNotBlank() // do we need to ensure there's always a description?
        }
    }
}

data class MethodUiState(
    val methodDetails : SplitMethodDetails = SplitMethodDetails(),
    val isEntryValid : Boolean = false
)
