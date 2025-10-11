package ca.tremblay95.billsplit.data.repository

import android.database.sqlite.SQLiteConstraintException
import ca.tremblay95.billsplit.common.BillSplitError
import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.data.data_source.OperandDao
import ca.tremblay95.billsplit.data.data_source.OperationDao
import ca.tremblay95.billsplit.data.data_source.SplitDao
import ca.tremblay95.billsplit.data.mappers.toSplit
import ca.tremblay95.billsplit.data.mappers.toSplitEntity
import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class OfflineSplitRepository(
    private val splitDao : SplitDao,
    private val operationDao : OperationDao,
    private val operandDao : OperandDao
) : SplitRepository {

    /**
     *  Split Method
     */
    override fun getAllSplits() : Flow<Result<List<Split>>> = flow {
        emit(Result.Loading)

        splitDao.getAllSplits()
            .map { splitEntities ->
                Result.Success(splitEntities.map { splitEntity ->
                    splitEntity.toSplit()
                })
            }
            .catch { e ->
                emit(Result.Error(BillSplitError.Unknown("Database error: ${e.message}")))
            }
            .collect { result ->
                emit(result)
            }
    }

    override fun getSplit(id : Int) : Flow<Result<Split>> = flow {
        emit(Result.Loading)

        splitDao.getSplit(id)
            .map { splitEntity ->
                if (splitEntity != null) {
                    Result.Success(splitEntity.toSplit())
                }
                else {
                    Result.NotFound
                }
            }
            .catch { e ->
                emit(Result.Error(BillSplitError.Unknown("Database error: ${e.message}")))
            }
            .collect { result ->
                emit(result)
            }
    }

    override suspend fun insertSplit(split : Split) : Result<Unit> {
        return try {
            val id = splitDao.insertSplit(split.toSplitEntity())
            if (id > 0) {
                Result.Success(Unit)
            }
            else {
                Result.Error(BillSplitError.Unknown("Insert failed"))
            }
        }
        catch (e : SQLiteConstraintException) {
            Result.Error(BillSplitError.DuplicateName)
        }
        catch (e : Exception) {
            Result.Error(BillSplitError.Unknown("Database error: ${e.message}"))
        }
    }

    override suspend fun deleteSplit(split : Split) : Result<Unit> {
        return try {
            val rows = splitDao.deleteSplit(split.toSplitEntity())
            if (rows > 0) {
                Result.Success(Unit)
            }
            else {
                Result.NotFound
            }
        }
        catch (e : Exception) {
            Result.Error(BillSplitError.Unknown("Database error: ${e.message}"))
        }
    }

    override suspend fun updateSplit(split : Split) : Result<Unit> {
        return try {
            val rows = splitDao.updateSplit(split.toSplitEntity())
            if (rows > 0) {
                Result.Success(Unit)
            }
            else {
                Result.NotFound
            }
        }
        catch (e : SQLiteConstraintException) {
            Result.Error(BillSplitError.DuplicateName)
        }
        catch (e : Exception) {
            Result.Error(BillSplitError.Unknown("Database error: ${e.message}"))
        }
    }

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
