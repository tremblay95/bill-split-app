package ca.tremblay95.billsplit.domain.model

abstract class Operation{
    abstract val operationType : OperationType
    abstract val operands : List<Operand>
    var subOperations : Array<Operation?> = arrayOf()

    abstract fun applyTo(total : Double) : List<Double>

    // TODO: Handle index out of range
    fun splitFurther(index : Int, operation : Operation) {
        subOperations[index] = operation
    }
}


