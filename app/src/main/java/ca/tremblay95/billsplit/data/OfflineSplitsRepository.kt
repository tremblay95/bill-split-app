package ca.tremblay95.billsplit.data

import ca.tremblay95.billsplit.data.daos.MethodDao
import ca.tremblay95.billsplit.data.daos.OperandDao
import ca.tremblay95.billsplit.data.daos.OperationDao
import ca.tremblay95.billsplit.data.models.SplitMethod
import ca.tremblay95.billsplit.data.models.SplitOperand
import ca.tremblay95.billsplit.data.models.SplitOperation
import kotlinx.coroutines.flow.Flow

class OfflineSplitsRepository(
    private val methodDao : MethodDao,
    private val operationDao : OperationDao,
    private val operandDao : OperandDao
) : SplitsRepository {

    /**
     *  Split Method
     */
    override fun getAllSplitMethods() : Flow<List<SplitMethod>> = methodDao.getAllSplitMethods()
    override suspend fun insertSplitMethod(method : SplitMethod) = methodDao.insertSplitMethod(method)
    override suspend fun deleteSplitMethod(method : SplitMethod) = methodDao.deleteSplitMethod(method)
    override suspend fun updateSplitMethod(method : SplitMethod) = methodDao.updateSplitMethod(method)

    /**
     *  Split Operation
     */
    override suspend fun insertSplitOperation(operation : SplitOperation) = operationDao.insertSplitOperation(operation)
    override suspend fun deleteSplitOperation(operation : SplitOperation) = operationDao.deleteSplitOperation(operation)
    override suspend fun updateSplitOperation(operation : SplitOperation) = operationDao.updateSplitOperation(operation)

    /**
     *  Split Operand
     */
    override suspend fun insertSplitOperand(operand : SplitOperand) = operandDao.insertSplitOperand(operand)
    override suspend fun deleteSplitOperand(operand : SplitOperand) = operandDao.deleteSplitOperand(operand)
    override suspend fun updateSplitOperand(operand : SplitOperand) = operandDao.updateSplitOperand(operand)
}
