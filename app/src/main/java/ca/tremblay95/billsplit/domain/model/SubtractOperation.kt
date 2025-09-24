package ca.tremblay95.billsplit.domain.model

class SubtractOperation(override val operands : List<Operand>) : Operation() {
    init {
        subOperations = Array(operands.size + 1) { null }
    }

    override val operationType = OperationType.Subtract
    override fun applyTo(total: Double): List<Double> {
        // TODO: handle subtrahends totaling more than total
        val remainder = total - operands.sumOf { it.value }
        return operands.mapIndexed { index, operand ->
            subOperations[index]?.applyTo(operand.value) ?: listOf(operand.value)
        }.flatten() + (subOperations.last()?.applyTo(remainder) ?: listOf(remainder))
    }
}
