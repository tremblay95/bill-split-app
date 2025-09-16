package ca.tremblay95.billsplit.ui.model

class SubtractiveSplitOp(val subtrahends : List<Subtrahend>) : SplitOperation() {
    init {
        subSplits = Array(subtrahends.size + 1) { null }
    }

    override val splitType = SplitType.Subtractive
    override fun applyTo(total: Double): List<Double> {
        // TODO: handle subtrahends totaling more than total
        val remainder = total - subtrahends.sumOf { it.value }
        return subtrahends.mapIndexed { index, subtrahend ->
            subSplits[index]?.applyTo(subtrahend.value) ?: listOf(subtrahend.value)
        }.flatten() + (subSplits.last()?.applyTo(remainder) ?: listOf(remainder))
    }

    class Subtrahend(
        val value : Double,
        val name : String = ""
    )
}