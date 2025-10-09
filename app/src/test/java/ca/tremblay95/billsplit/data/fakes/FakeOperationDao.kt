package ca.tremblay95.billsplit.data.fakes

import ca.tremblay95.billsplit.data.data_source.OperationDao
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.data.relation.OperationWithOperands
import kotlinx.coroutines.flow.Flow

class FakeOperationDao : OperationDao{
    override fun getOperationWithOperands(id : Int) : Flow<OperationWithOperands?> {
        TODO("Not yet implemented")
    }

    override fun getChildOperations(parentId : Int) : Flow<List<OperationEntity>> {
        TODO("Not yet implemented")
    }

    override fun getOperationsWithOperands(ids : List<Int?>) : Flow<Array<OperationWithOperands>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertOperation(operation : OperationEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOperation(operationEntity : OperationEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOperation(operationEntity : OperationEntity) {
        TODO("Not yet implemented")
    }

}
