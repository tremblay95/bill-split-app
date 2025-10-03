package ca.tremblay95.billsplit.data.repository

import ca.tremblay95.billsplit.data.data_source.OperandDao
import ca.tremblay95.billsplit.data.data_source.OperationDao
import ca.tremblay95.billsplit.data.data_source.SplitDao
import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.data.model.SplitEntity
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import kotlinx.coroutines.flow.Flow

class OfflineSplitRepository(
    private val splitDao : SplitDao,
    private val operationDao : OperationDao,
    private val operandDao : OperandDao
) : SplitRepository {

    /**
     *  Split Method
     */
    override fun getAllSplits() : Flow<List<SplitEntity>> = splitDao.getAllSplits()
    override suspend fun insertSplit(split : SplitEntity) = splitDao.insertSplit(split)
    override suspend fun deleteSplit(split : SplitEntity) = splitDao.deleteSplit(split)
    override suspend fun updateSplit(split : SplitEntity) = splitDao.updateSplit(split)

    /**
     *  Split Operation
     */
    override suspend fun insertOperation(operation : OperationEntity) = operationDao.insertOperation(operation)
    override suspend fun deleteOperation(operation : OperationEntity) = operationDao.deleteOperation(operation)
    override suspend fun updateOperation(operation : OperationEntity) = operationDao.updateOperation(operation)

    /**
     *  Split Operand
     */
    override suspend fun insertOperand(operand : OperandEntity) = operandDao.insertOperand(operand)
    override suspend fun deleteOperand(operand : OperandEntity) = operandDao.deleteOperand(operand)
    override suspend fun updateOperand(operand : OperandEntity) = operandDao.updateOperand(operand)
}
