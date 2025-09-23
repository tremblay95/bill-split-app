package ca.tremblay95.billsplit.ui.model

class FractionalSplitOperation(id : Int = 0, val fractions : List<SplitOperandDetails>) : SplitOperationDetails() {
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
