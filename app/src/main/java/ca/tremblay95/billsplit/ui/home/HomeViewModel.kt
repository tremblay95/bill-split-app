package ca.tremblay95.billsplit.ui.home

import androidx.lifecycle.ViewModel
import ca.tremblay95.billsplit.data.SplitSchema

class HomeViewModel : ViewModel() {
// TODO: retrieve all splits from Room database 
}

data class HomeUiState(val splitList : List<SplitSchema> = listOf())
