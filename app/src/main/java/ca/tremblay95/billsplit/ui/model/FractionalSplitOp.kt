package ca.tremblay95.billsplit.ui.model

class FractionalSplitOperation(val fractions : List<Fraction>) : SplitOperation() {
    // TODO: validate fractions to ensure they total 1.0
    init {
        subSplits = Array(fractions.size) { null }
    }

    override val splitType = SplitType.Fractional
    override fun applyTo(total : Double): List<Double> {
        return fractions.mapIndexed { index, fraction ->
            val frac = fraction.value * total
            subSplits[index]?.applyTo(frac) ?: listOf(frac)
        }.flatten()
    }

    class Fraction (
        val value : Double,
        val name : String = ""
    )
}
