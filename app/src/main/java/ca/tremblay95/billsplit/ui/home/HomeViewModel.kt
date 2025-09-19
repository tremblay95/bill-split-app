package ca.tremblay95.billsplit.ui.home

import androidx.lifecycle.ViewModel
import ca.tremblay95.billsplit.data.DevelopmentLocalDataProvider
import ca.tremblay95.billsplit.ui.model.SplitMethodDetails

class HomeViewModel : ViewModel() {
// TODO: retrieve all splits from Room database
    val homeUiState = HomeUiState(DevelopmentLocalDataProvider.methodDetails)
}

data class HomeUiState(val splitList : List<SplitMethodDetails> = listOf())
