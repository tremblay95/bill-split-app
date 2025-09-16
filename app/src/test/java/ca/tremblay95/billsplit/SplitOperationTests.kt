package ca.tremblay95.billsplit

import ca.tremblay95.billsplit.ui.model.FractionalSplitOperation
import ca.tremblay95.billsplit.ui.model.FractionalSplitOperation.Fraction
import ca.tremblay95.billsplit.ui.model.SubtractiveSplitOp
import ca.tremblay95.billsplit.ui.model.SubtractiveSplitOp.Subtrahend
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SplitOperationTests {

    @Test
    fun fractionalSplit_splitsCorrectly() {
        val split = listOf(0.3, 0.5, 0.2)
        val total = 100.0
        val expected = listOf(30.0, 50.0, 20.0)

        val splitOp = FractionalSplitOperation(split.map { Fraction(it) })
        val actual = splitOp.applyTo(total)

        assertEquals(expected, actual)
    }

    @Test
    fun fractionalSplit_fractionalSubSplit_splitsCorrectly() {
        val baseSplit = listOf(0.3, 0.5, 0.2)
        val subSplit = listOf(0.5, 0.5)
        val expected = listOf(30.0, 25.0, 25.0, 20.0)

        val total = 100.0

        val baseSplitOp = FractionalSplitOperation(baseSplit.map { Fraction(it) })
        val subSplitOp = FractionalSplitOperation(subSplit.map { Fraction(it) })

        baseSplitOp.splitFurther(1, subSplitOp)
        val actual = baseSplitOp.applyTo(total)

        assertEquals(expected, actual)
    }

    @Test
    fun subtractiveSplit_splitsCorrectly() {
        val total = 100.0
        val split = listOf(42.0, 32.0)
        val expected = listOf(42.0, 32.0, 26.0)

        val splitOp = SubtractiveSplitOp(split.map{ Subtrahend(it) })
        val actual = splitOp.applyTo(total)

        assertEquals(expected, actual)
    }

    @Test
    fun subtractiveSplit_subtractiveSubSplit_splitsCorrectly() {
        val total = 100.0
        val baseSplit = listOf(42.0, 32.0)
        val subSplit = listOf(10.0, 13.0)
        val expected = listOf(42.0, 10.0, 13.0, 9.0, 26.0)

        val baseSplitOp = SubtractiveSplitOp(baseSplit.map { Subtrahend(it) })
        val subSplitOp = SubtractiveSplitOp(subSplit.map { Subtrahend(it)})

        baseSplitOp.splitFurther(1, subSplitOp)

        val actual = baseSplitOp.applyTo(total)
        assertEquals(expected, actual)
    }

    @Test
    fun subtractiveSplit_fractionalSubSplit_splitsCorrectly() {
        val total = 120.0
        val expected = listOf(20.0, 30.0, 50.0, 20.0)

        val baseSplitOp = SubtractiveSplitOp(listOf(Subtrahend(20.0)))
        val subSplitOp = FractionalSplitOperation(listOf(0.3, 0.5, 0.2).map { Fraction(it) })

        baseSplitOp.splitFurther(1, subSplitOp)
        val actual = baseSplitOp.applyTo(total)

        assertEquals(expected, actual)
    }

    @Test
    fun fractionalSplit_subtractiveSubSplit_splitsCorrectly() {
        val total = 100.0
        val expected = listOf(30.0, 20.0, 30.0, 20.0)

        val baseSplitOp = FractionalSplitOperation(listOf(0.3, 0.5, 0.2).map { Fraction(it) })
        val subSplitOp = SubtractiveSplitOp(listOf(Subtrahend(20.0)))

        baseSplitOp.splitFurther(1, subSplitOp)
        val actual = baseSplitOp.applyTo(total)

        assertEquals(expected, actual)
    }
}