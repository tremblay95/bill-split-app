package ca.tremblay95.billsplit.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import ca.tremblay95.billsplit.data.models.SplitOperation

data class OperationWithSubOpsAndOperands(
    @Embedded val operation : SplitOperation,

    @Relation(
        entity = SplitOperation::class,
        parentColumn = "operation_id",
        entityColumn = "parent_id"
    )
    val subOperations : List<OperationWithOperands>
)
