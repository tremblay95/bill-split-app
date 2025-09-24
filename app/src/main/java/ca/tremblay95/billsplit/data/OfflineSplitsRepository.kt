package ca.tremblay95.billsplit.data

import ca.tremblay95.billsplit.data.dao.SplitDao
import ca.tremblay95.billsplit.data.dao.OperandDao
import ca.tremblay95.billsplit.data.dao.OperationDao
import ca.tremblay95.billsplit.data.model.SplitEntity
import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity
import kotlinx.coroutines.flow.Flow

class OfflineSplitsRepository(
    private val splitDao : SplitDao,
    private val operationDao : OperationDao,
    private val operandDao : OperandDao
) : SplitsRepository {

    /**
     *  Split Method
     */
    override fun getAllSplits() : Flow<List<SplitEntity>> = splitDao.getAllSplitMethods()
    override suspend fun insertSplit(split : SplitEntity) = splitDao.insertSplitMethod(split)
    override suspend fun deleteSplit(split : SplitEntity) = splitDao.deleteSplitMethod(split)
    override suspend fun updateSplit(method : SplitEntity) = splitDao.updateSplitMethod(method)

    /**
     *  Split Operation
     */
    override suspend fun insertOperation(operation : OperationEntity) = operationDao.insertSplitOperation(operation)
    override suspend fun deleteOperation(operation : OperationEntity) = operationDao.deleteSplitOperation(operation)
    override suspend fun updateOperation(operation : OperationEntity) = operationDao.updateSplitOperation(operation)

    /**
     *  Split Operand
     */
    override suspend fun insertOperand(operand : OperandEntity) = operandDao.insertSplitOperand(operand)
    override suspend fun deleteOperand(operand : OperandEntity) = operandDao.deleteSplitOperand(operand)
    override suspend fun updateOperand(operand : OperandEntity) = operandDao.updateSplitOperand(operand)
}
