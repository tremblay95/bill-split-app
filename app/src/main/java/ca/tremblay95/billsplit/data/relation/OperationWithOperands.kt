package ca.tremblay95.billsplit.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity

data class OperationWithOperands(
    @Embedded val operation : OperationEntity,

    @Relation(
        parentColumn = "operation_id",
        entityColumn = "operation_id"
    )
    val operands : List<OperandEntity>
)
