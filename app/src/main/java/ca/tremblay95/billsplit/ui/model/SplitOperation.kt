package ca.tremblay95.billsplit.ui.model

enum class SplitType {
    Fractional,
    Subtractive
}

abstract class SplitOperation {
    abstract val splitType : SplitType

    var subSplits : Array<SplitOperation?> = arrayOf()

    abstract fun applyTo(total : Double) : List<Double>

    // TODO: Handle index out of range
    fun splitFurther(index : Int, operation : SplitOperation) {
        subSplits[index] = operation
    }
}

class SplitOperand (
    val value : Double,
    val name : String = ""
)
