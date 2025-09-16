package ca.tremblay95.billsplit.ui.model

data class SplitMethod(
    val id: Int,
    val name: String,
    val description: String,
    val splitOperation: SplitOperation? = null // TODO: Make this non-nullable
    // TODO: Complete this class
)