package ca.tremblay95.billsplit.presentation.split_list

import ca.tremblay95.billsplit.domain.model.Split

data class SplitListState(
    val isLoading : Boolean = false,
    val splitList : List<Split> = listOf(),
    val error : String = ""
)
