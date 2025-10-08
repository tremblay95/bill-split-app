package ca.tremblay95.billsplit.domain.fakes

import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.data.model.SplitEntity
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSplitRepository : SplitRepository {

    var splitEntities : MutableList<SplitEntity> = mutableListOf()
    var operationEntities : MutableList<OperationEntity> = mutableListOf()
    var operandEntities : MutableList<OperandEntity> = mutableListOf()



    override fun getAllSplits() : Flow<List<SplitEntity>> = flow {
        emit(splitEntities)
    }

    override fun getSplit(id : Int) : Flow<SplitEntity> = flow {
        emit(splitEntities.filter { it.splitId == id }.first())
    }

    override suspend fun insertSplit(split : SplitEntity) {
        splitEntities.add(split)
    }

    override suspend fun deleteSplit(split : SplitEntity) {
        splitEntities.remove(split)
    }

    override suspend fun updateSplit(split : SplitEntity) {
        val index = splitEntities.indexOfFirst { it.splitId == split.splitId }
        splitEntities[index] = split
    }

    override suspend fun insertOperation(operation : OperationEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOperation(operation : OperationEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOperation(operation : OperationEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertOperand(operand : OperandEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOperand(operand : OperandEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOperand(operand : OperandEntity) {
        TODO("Not yet implemented")
    }
}
