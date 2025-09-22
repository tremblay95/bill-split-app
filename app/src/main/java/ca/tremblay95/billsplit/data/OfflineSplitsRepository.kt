package ca.tremblay95.billsplit.data

import ca.tremblay95.billsplit.data.daos.MethodDao
import ca.tremblay95.billsplit.data.daos.OperandDao
import ca.tremblay95.billsplit.data.daos.OperationDao
import ca.tremblay95.billsplit.data.models.SplitMethod
import ca.tremblay95.billsplit.data.models.SplitOperand
import ca.tremblay95.billsplit.data.models.SplitOperation
import ca.tremblay95.billsplit.data.relations.OperationWithOperands
import ca.tremblay95.billsplit.ui.model.SplitMethodDetails
import ca.tremblay95.billsplit.ui.model.SplitOperationDetails
import ca.tremblay95.billsplit.ui.model.toSplitMethodDetails
import ca.tremblay95.billsplit.ui.model.toSplitOperationDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class OfflineSplitsRepository(
    private val methodDao : MethodDao,
    private val operationDao : OperationDao,
    private val operandDao : OperandDao
) : SplitsRepository {
    override fun getSplitMethodDetails(id : Int) : Flow<SplitMethodDetails?> {
        return getSplitMethod(id)
            .map { method ->
                if (method == null) null else {

                    var methodDetails = method?.toSplitMethodDetails()

                    if (methodDetails != null && method.operationId != null) {
                        getSplitOperationDetails(method.operationId).collect {
                            methodDetails = methodDetails!!.copy(splitOperation = it)
                        }
                    }

                    methodDetails
                }
            }
    }

    override fun getSplitOperationDetails(id : Int) : Flow<SplitOperationDetails?> {
        return getOperationWithOperands(id).map { operationWithOperands ->
            var operationDetails = operationWithOperands!!.toSplitOperationDetails()

            // I don't think this works with null child operations
            getChildOperations(id).collect { childOps ->
                combine(
                childOps
                    .sortedBy { childOp -> childOp.parentOperationIndex }
                    .map { childOp -> getSplitOperationDetails(childOp.operationId) }
                ) { it }.collect {
                    operationDetails.subSplits = it
                }
            }

            operationDetails
        }
    }



    /**
     *  Split Method
     */
    override fun getSplitMethod(id : Int) : Flow<SplitMethod?> = methodDao.getSplitMethod(id)
    override fun getAllSplitMethods() : Flow<List<SplitMethod>> = methodDao.getAllSplitMethods()
    override suspend fun insertSplitMethod(method : SplitMethod) = methodDao.insertSplitMethod(method)
    override suspend fun deleteSplitMethod(method : SplitMethod) = methodDao.deleteSplitMethod(method)
    override suspend fun updateSplitMethod(method : SplitMethod) = methodDao.updateSplitMethod(method)

    /**
     *  Split Operation
     */
    override fun getOperationWithOperands(id : Int) : Flow<OperationWithOperands?> = operationDao.getOperationWithOperands(id)
    override fun getChildOperations(parentId : Int) : Flow<List<SplitOperation>> = operationDao.getChildOperations(parentId)
    override fun getOperationsWithOperands(ids : List<Int?>) : Flow<Array<OperationWithOperands?>> = operationDao.getOperationsWithOperands(ids)
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
