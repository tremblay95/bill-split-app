package ca.tremblay95.billsplit.ui.model

import ca.tremblay95.billsplit.data.relations.OperationWithOperands

enum class SplitType {
    Fractional,
    Subtractive
}

abstract class SplitOperationDetails {
    val id : Int = 0
    abstract val splitType : SplitType

    protected var subSplits : Array<SplitOperationDetails?> = arrayOf()

    abstract fun applyTo(total : Double) : List<Double>

    // TODO: Handle index out of range
    fun splitFurther(index : Int, operation : SplitOperationDetails) {
        subSplits[index] = operation
    }
}

data class SplitOperandDetails (
    val value : Double,
    val name : String = ""
)

fun OperationWithOperands.toSplitOperationDetails() : SplitOperationDetails =
    when (operation.splitType) {
        SplitType.Fractional -> FractionalSplitOperation(operation.operationId,operands.map { SplitOperandDetails(it.value, it.name)})
        SplitType.Subtractive -> SubtractiveSplitOp(operation.operationId, operands.map { SplitOperandDetails(it.value, it.name)})
    }
