package ca.tremblay95.billsplit.domain.fakes

import ca.tremblay95.billsplit.common.BillSplitError
import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.domain.model.Operand
import ca.tremblay95.billsplit.domain.model.Operation
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSplitRepository : SplitRepository {

    var splits = mutableMapOf<Int, Split>()
    var operation = mutableMapOf<Int, Operation>()
    var operand = mutableMapOf<Int, Operand>()

    override fun getAllSplits() : Flow<Result<List<Split>>> = flow {
        emit(Result.Success(splits.values.toList()))
    }

    override fun getSplit(id : Int) : Flow<Result<Split>> = flow {
        val split = splits[id]
        emit(if (split != null) Result.Success(split) else Result.NotFound)
    }

    override suspend fun insertSplit(split : Split) : Result<Unit> {
        return if (splits.containsKey(split.id)) {
            Result.Error(BillSplitError.DuplicateName)
        } else {
            splits[split.id] = (split)
            Result.Success(Unit)
        }
    }

    override suspend fun deleteSplit(split : Split) : Result<Unit> {
        return if (splits.remove(split.id) != null) Result.Success(Unit)
        else Result.NotFound
    }

    override suspend fun updateSplit(split : Split) : Result<Unit> {
        return if (splits.containsKey(split.id)) {
            splits[split.id] = split
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
