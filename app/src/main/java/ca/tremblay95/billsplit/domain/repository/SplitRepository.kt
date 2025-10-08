package ca.tremblay95.billsplit.domain.repository

import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.domain.model.Split
import kotlinx.coroutines.flow.Flow

// TODO: Consider inserting, updating, and deleting lists of each entity.
interface SplitRepository {

    /**
     *  Split
     */
    fun getAllSplits() : Flow<Result<List<Split>>>
    fun  getSplit(id : Int) : Flow<Result<Split>>
    suspend fun insertSplit(split : Split) : Result<Unit>
    suspend fun deleteSplit(split : Split) : Result<Unit>
    suspend fun updateSplit(split : Split) : Result<Unit>


    // TODO: Domain layer should not know about Entities from the Data layer
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
