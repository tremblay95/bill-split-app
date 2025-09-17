package ca.tremblay95.billsplit.ui.home

import androidx.lifecycle.ViewModel
import ca.tremblay95.billsplit.data.DevelopmentLocalDataProvider
import ca.tremblay95.billsplit.ui.model.SplitMethod

class HomeViewModel : ViewModel() {
// TODO: retrieve all splits from Room database
    val homeUiState = HomeUiState(DevelopmentLocalDataProvider.splitMethods)
}

data class HomeUiState(val splitList : List<SplitMethod> = listOf())
