package ca.tremblay95.billsplit.ui.model

import ca.tremblay95.billsplit.data.models.SplitMethod

data class SplitMethodDetails(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val splitOperation: SplitOperationDetails? = null // TODO: Make this non-nullable
    // TODO: Complete this class
)

// TODO: extension functions for going between the ui model and the data model
fun SplitMethodDetails.toSplitMethod() : SplitMethod =
    SplitMethod(id, name, description)

fun SplitMethod.toSplitMethodDetails() : SplitMethodDetails =
    SplitMethodDetails(methodId, name, description)
