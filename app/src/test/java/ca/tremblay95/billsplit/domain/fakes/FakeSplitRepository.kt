package ca.tremblay95.billsplit.domain.fakes

import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSplitRepository : SplitRepository {

    var splits : MutableList<Split> = mutableListOf()
    var operationEntities : MutableList<OperationEntity> = mutableListOf()
    var operandEntities : MutableList<OperandEntity> = mutableListOf()



    override fun getAllSplits() : Flow<Result<List<Split>>> = flow {
        emit(Result.Success(splits))
    }

    override fun getSplit(id : Int) : Flow<Result<Split>> = flow {
        val split = splits.filter { it.id == id }.firstOrNull()
        emit(if (split != null) Result.Success(split) else Result.NotFound)
    }

    override suspend fun insertSplit(split : Split) : Result<Unit> {
        return if (splits.contains(split)) {
            Result.Error("Split already exists.")
        } else {
            splits.add(split)
            Result.Success(Unit)
        }
    }

    override suspend fun deleteSplit(split : Split) : Result<Unit> {
        return if (splits.remove(split)) Result.Success(Unit)
        else Result.NotFound
    }

    override suspend fun updateSplit(split : Split) : Result<Unit> {
        val index = splits.indexOfFirst { it.id == split.id }

        return if (index >= 0) {
            splits[index] = split
            Result.Success(Unit)
        }
        else Result.NotFound
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
