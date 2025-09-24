package ca.tremblay95.billsplit.presentation.create_split

import ca.tremblay95.billsplit.domain.model.Split

data class CreateSplitState(
    val split : Split = Split(),
    val isEntryValid : Boolean = false
)
