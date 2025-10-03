package ca.tremblay95.billsplit.domain.repository

import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.data.model.SplitEntity
import kotlinx.coroutines.flow.Flow

// TODO: Consider inserting, updating, and deleting lists of each entity.
interface SplitRepository {

    /**
     *  Split
     */
    fun getAllSplits() : Flow<List<SplitEntity>>
    fun  getSplit(id : Int) : Flow<SplitEntity>
    suspend fun insertSplit(split : SplitEntity)
    suspend fun deleteSplit(split : SplitEntity)
    suspend fun updateSplit(split : SplitEntity)

    /**
     *  Operation
     */
    suspend fun insertOperation(operation : OperationEntity)
    suspend fun deleteOperation(operation : OperationEntity)
    suspend fun updateOperation(operation : OperationEntity)

    /**
     *  Operand
     */
    suspend fun insertOperand(operand : OperandEntity)
    suspend fun deleteOperand(operand : OperandEntity)
    suspend fun updateOperand(operand : OperandEntity)
}
