package ca.tremblay95.billsplit.data

import ca.tremblay95.billsplit.data.models.SplitMethod
import ca.tremblay95.billsplit.data.models.SplitOperand
import ca.tremblay95.billsplit.data.models.SplitOperation
import ca.tremblay95.billsplit.data.relations.OperationWithOperands
import ca.tremblay95.billsplit.ui.model.SplitMethodDetails
import ca.tremblay95.billsplit.ui.model.SplitOperationDetails
import kotlinx.coroutines.flow.Flow

// TODO: Consider inserting, updating, and deleting lists of each entity.
interface SplitsRepository {

    fun getSplitMethodDetails(id : Int) : Flow<SplitMethodDetails?>
    fun getSplitOperationDetails(id : Int) : Flow<SplitOperationDetails?>

    /**
     *  Split Method
     */
    fun getSplitMethod(id : Int) : Flow<SplitMethod?>
    fun getAllSplitMethods() : Flow<List<SplitMethod>>
    suspend fun insertSplitMethod(method : SplitMethod)
    suspend fun deleteSplitMethod(method : SplitMethod)
    suspend fun updateSplitMethod(method : SplitMethod)

    /**
     *  Split Operation
     */
    fun getOperationWithOperands(id : Int) : Flow<OperationWithOperands?>
    fun getChildOperations(parentId : Int) : Flow<List<SplitOperation>>
    fun getOperationsWithOperands(ids : List<Int?>) : Flow<Array<OperationWithOperands>>
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
