package ca.tremblay95.billsplit.data.model

import ca.tremblay95.billsplit.ui.model.SplitOperand
import ca.tremblay95.billsplit.ui.model.SplitType

data class SplitOperation(
    val id : Int,
    val methodId : Int,
    val parentId : Int?,
    val type : SplitType,
    val operands : List<SplitOperand>
)
