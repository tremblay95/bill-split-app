package ca.tremblay95.billsplit.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import ca.tremblay95.billsplit.data.models.SplitMethod
import ca.tremblay95.billsplit.data.models.SplitOperation

data class MethodWithOperationsAndOperands(
    @Embedded val method : SplitMethod,

    @Relation(
        entity = SplitOperation::class,
        parentColumn = "method_id",
        entityColumn = "method_id"
    )
    val operation : OperationWithSubOpsAndOperands
)
