package ca.tremblay95.billsplit.ui.model

// TODO: Rename to SplitMethodDetails
data class SplitMethod(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val splitOperation: SplitOperation? = null // TODO: Make this non-nullable
    // TODO: Complete this class
)

// TODO: extension functions for going between the ui model and the data model
