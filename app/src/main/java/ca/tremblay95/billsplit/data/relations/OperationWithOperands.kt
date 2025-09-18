package ca.tremblay95.billsplit.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import ca.tremblay95.billsplit.data.models.SplitOperand
import ca.tremblay95.billsplit.data.models.SplitOperation

data class OperationWithOperands(
    @Embedded val operation : SplitOperation,

    @Relation(
        parentColumn = "operation_id",
        entityColumn = "operation_id"
    )
    val operands : List<SplitOperand>
)
