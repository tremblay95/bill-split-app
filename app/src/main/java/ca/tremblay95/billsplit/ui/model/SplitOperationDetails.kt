package ca.tremblay95.billsplit.ui.model

import ca.tremblay95.billsplit.data.relations.OperationWithOperands

enum class SplitType {
    Fractional,
    Subtractive
}

abstract class SplitOperationDetails {
    abstract val splitType : SplitType

    var subSplits : Array<SplitOperationDetails?> = arrayOf()

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
        SplitType.Fractional -> FractionalSplitOperation(operands.map { SplitOperandDetails(it.value, it.name)})
        SplitType.Subtractive -> SubtractiveSplitOp(operands.map { SplitOperandDetails(it.value, it.name)})
    }
