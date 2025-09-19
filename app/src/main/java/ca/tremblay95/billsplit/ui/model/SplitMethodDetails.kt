package ca.tremblay95.billsplit.ui.model

data class SplitMethodDetails(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val splitOperation: SplitOperation? = null // TODO: Make this non-nullable
    // TODO: Complete this class
)

// TODO: extension functions for going between the ui model and the data model
fun SplitMethodDetails.toSplitMethod() : ca.tremblay95.billsplit.data.models.SplitMethod =
    ca.tremblay95.billsplit.data.models.SplitMethod(
        methodId = id,
        name = name,
        description = description
    )
