package ca.tremblay95.billsplit.ui.model

class FractionalSplitOperation(val fractions : List<SplitOperand>) : SplitOperation() {
    // TODO: validate fractions to ensure they total 1.0
    init {
        subSplits = Array(fractions.size) { null }
    }

    override val splitType = SplitType.Fractional
    override fun applyTo(total : Double): List<Double> {
        return fractions.mapIndexed { index, operand ->
            val frac = operand.value * total
            subSplits[index]?.applyTo(frac) ?: listOf(frac)
        }.flatten()
    }
}
