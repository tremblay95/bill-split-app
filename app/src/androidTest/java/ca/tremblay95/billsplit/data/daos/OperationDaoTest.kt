package ca.tremblay95.billsplit.data.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ca.tremblay95.billsplit.data.SplitDatabase
import ca.tremblay95.billsplit.data.models.SplitOperand
import ca.tremblay95.billsplit.data.models.SplitOperation
import ca.tremblay95.billsplit.data.relations.OperationWithOperands
import ca.tremblay95.billsplit.ui.model.SplitType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class OperationDaoTest {

    private lateinit var operationDao : OperationDao
    private lateinit var db : SplitDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            SplitDatabase::class.java
        ).allowMainThreadQueries().build()
        operationDao = db.operationDao()
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        db.close()
    }

    @Test
    fun insertSplitOperation_splitOperationInsertedSuccessfully() = runTest {
        val operation = SplitOperation(1, null, SplitType.Fractional, null)

        operationDao.insertSplitOperation(operation)

        val result = operationDao.getOperationWithOperands(operation.operationId).first()
        assertThat(result?.operation).isEqualTo(operation)
    }

    @Test
    fun deleteSplitOperation_splitOperationDeletedSuccessfully() = runTest {
        val operation = SplitOperation(1, null, SplitType.Fractional, null)

        operationDao.insertSplitOperation(operation)
        operationDao.deleteSplitOperation(operation)

        val result = operationDao.getOperationWithOperands(operation.operationId).first()
        assertThat(result).isNull()
    }

    @Test
    fun updateSplitOperation_splitOperationUpdatedSuccessfully() = runTest {
        val operation = SplitOperation(1, null, SplitType.Fractional, null)
        val updatedOperation = operation.copy(splitType = SplitType.Subtractive)

        operationDao.insertSplitOperation(operation)
        operationDao.updateSplitOperation(updatedOperation)

        val result = operationDao.getOperationWithOperands(operation.operationId).first()
        assertThat(result?.operation).isEqualTo(updatedOperation)
    }

    @Test
    fun getChildOperations_noChildren_emptyListReturned() = runTest {
        val operation = SplitOperation(1, null, SplitType.Fractional, null)

        operationDao.insertSplitOperation(operation)

        val result = operationDao.getChildOperations(operation.operationId).first()
        assertThat(result).isEqualTo(emptyList<SplitOperation>())
    }

    @Test
    fun getChildOperations_threeChildren_childrenReturnedSuccessfully() = runTest {
        val parent = SplitOperation(1, null, SplitType.Fractional, null)
        val children = (1..3).map {
            SplitOperation(it + 1, parent.operationId, SplitType.Fractional, it)
        }

        operationDao.insertSplitOperation(parent)
        children.forEach { operationDao.insertSplitOperation(it) }

        val result = operationDao.getChildOperations(parent.operationId).first()
        assertThat(result).isEqualTo(children)
    }

    @Test
    fun getOperationWithOperands_operationWithOperandsReturnedSuccessfully() = runTest {
        val operandDao = db.operandDao()

        val operation = SplitOperation(1, null, SplitType.Fractional, null)
        val operands = (1..3).map {
            SplitOperand(it, 1.0 / it, "operand$it", it - 1, operation.operationId)
        }

        operationDao.insertSplitOperation(operation)
        operands.forEach { operandDao.insertSplitOperand(it) }

        val result = operationDao.getOperationWithOperands(operation.operationId).first()
        assertThat(result).isEqualTo(OperationWithOperands(operation, operands))
    }
}
