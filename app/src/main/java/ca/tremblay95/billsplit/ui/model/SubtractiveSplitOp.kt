package ca.tremblay95.billsplit.ui.model

class SubtractiveSplitOp(val subtrahends : List<SplitOperandDetails>) : SplitOperationDetails() {
    init {
        subSplits = Array(subtrahends.size + 1) { null }
    }

    override val splitType = SplitType.Subtractive
    override fun applyTo(total: Double): List<Double> {
        // TODO: handle subtrahends totaling more than total
        val remainder = total - subtrahends.sumOf { it.value }
        return subtrahends.mapIndexed { index, operand ->
            subSplits[index]?.applyTo(operand.value) ?: listOf(operand.value)
        }.flatten() + (subSplits.last()?.applyTo(remainder) ?: listOf(remainder))
    }
}
