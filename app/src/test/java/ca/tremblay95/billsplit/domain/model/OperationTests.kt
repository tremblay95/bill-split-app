package ca.tremblay95.billsplit.domain.model

import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class OperationTests {

    @Test
    fun divideOperation_splitsCorrectly() {
        val opValues = listOf(0.3, 0.5, 0.2)
        val total = 100.0
        val expected = listOf(30.0, 50.0, 20.0)

        val op = DivideOperation(opValues.map { Operand(it) })
        val actual = op.applyTo(total)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun divideOperationWithDivideSubOperation_splitsCorrectly() {
        val parentOpValues = listOf(0.3, 0.5, 0.2)
        val childOpValues = listOf(0.5, 0.5)
        val expected = listOf(30.0, 25.0, 25.0, 20.0)

        val total = 100.0

        val parentOp = DivideOperation(parentOpValues.map { Operand(it) })
        val childOp = DivideOperation(childOpValues.map { Operand(it) })

        parentOp.splitFurther(1, childOp)
        val actual = parentOp.applyTo(total)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun subtractOperation_splitsCorrectly() {
        val total = 100.0
        val opValues = listOf(42.0, 32.0)
        val expected = listOf(42.0, 32.0, 26.0)

        val op = SubtractOperation(opValues.map{ Operand(it) })
        val actual = op.applyTo(total)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun subtractOperationWithSubtractSubOperation_splitsCorrectly() {
        val total = 100.0
        val parentOpValues = listOf(42.0, 32.0)
        val childOpValues = listOf(10.0, 13.0)
        val expected = listOf(42.0, 10.0, 13.0, 9.0, 26.0)

        val parentOp = SubtractOperation(parentOpValues.map { Operand(it) })
        val childOp = SubtractOperation(childOpValues.map { Operand(it)})

        parentOp.splitFurther(1, childOp)

        val actual = parentOp.applyTo(total)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun subtractOperationWithDivideSubOperation_splitsCorrectly() {
        val total = 120.0
        val expected = listOf(20.0, 30.0, 50.0, 20.0)

        val parentOp = SubtractOperation(listOf(Operand(20.0)))
        val childOp = DivideOperation(listOf(0.3, 0.5, 0.2).map { Operand(it) })

        parentOp.splitFurther(1, childOp)
        val actual = parentOp.applyTo(total)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun divideOperationWithSubtractSubOperation_splitsCorrectly() {
        val total = 100.0
        val expected = listOf(30.0, 20.0, 30.0, 20.0)

        val parentOp = DivideOperation(listOf(0.3, 0.5, 0.2).map { Operand(it) })
        val childOp = SubtractOperation(listOf(Operand(20.0)))

        parentOp.splitFurther(1, childOp)
        val actual = parentOp.applyTo(total)

        Assert.assertEquals(expected, actual)
    }
}
