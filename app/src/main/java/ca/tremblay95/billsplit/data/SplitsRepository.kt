package ca.tremblay95.billsplit.data

import ca.tremblay95.billsplit.data.relations.MethodWithOperationsAndOperands
import ca.tremblay95.billsplit.data.models.SplitMethod
import ca.tremblay95.billsplit.data.models.SplitOperand
import ca.tremblay95.billsplit.data.models.SplitOperation
import kotlinx.coroutines.flow.Flow

// TODO: Consider inserting, updating, and deleting lists of each entity.
interface SplitsRepository {

    /**
     *  Split Methods with Operations and Operands
     */
    fun getMethodWithOperationsAndOperands(id : Int) : Flow<MethodWithOperationsAndOperands?>

    /**
     *  Split Method
     */
    fun getAllSplitMethods() : Flow<List<SplitMethod>>
    suspend fun insertSplitMethod(method : SplitMethod)
    suspend fun deleteSplitMethod(method : SplitMethod)
    suspend fun updateSplitMethod(method : SplitMethod)

    /**
     *  Split Operation
     */
    suspend fun insertSplitOperation(operation : SplitOperation)
    suspend fun deleteSplitOperation(operation : SplitOperation)
    suspend fun updateSplitOperation(operation : SplitOperation)

    /**
     *  Split Operand
     */
    suspend fun insertSplitOperand(operand : SplitOperand)
    suspend fun deleteSplitOperand(operand : SplitOperand)
    suspend fun updateSplitOperand(operand : SplitOperand)
}
