package ca.tremblay95.billsplit.domain.model

class DivideOperation(override val operands : List<Operand>) : Operation() {
    // TODO: validate fractions to ensure they total 1.0
    init {
        subOperations = Array(operands.size) { null }
    }

    override val operationType = OperationType.Divide
    override fun applyTo(total : Double): List<Double> {
        return operands.mapIndexed { index, operand ->
            val frac = operand.value * total
            subOperations[index]?.applyTo(frac) ?: listOf(frac)
        }.flatten()
    }
}
