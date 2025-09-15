package ca.tremblay95.billsplit.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
// TODO: retrieve all splits from Room database 
}

// TODO: replace string with the SplitSchema class
data class HomeUiState(val splitList : List<String> = listOf())
