package ca.tremblay95.billsplit.data.fakes

import ca.tremblay95.billsplit.data.data_source.SplitDao
import ca.tremblay95.billsplit.data.model.SplitEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSplitDao : SplitDao {
    val splitEntities = mutableMapOf<Int, SplitEntity>()
    private var shouldThrowException : Boolean = false
    
    fun setShouldThrowException(shouldThrow : Boolean = true) {
        shouldThrowException = shouldThrow
    }

    override fun getAllSplits() : Flow<List<SplitEntity>> = flow {
        if (shouldThrowException) {
            throw Exception("some exception")
        }
        else {
            emit(splitEntities.values.toList())
        }
    }

    override fun getSplit(splitId : Int) : Flow<SplitEntity?> = flow {
        if (shouldThrowException) {
            throw Exception("some exception")
        }
        else {
            emit(splitEntities[splitId])
        }
    }

    override suspend fun insertSplit(splitEntity : SplitEntity) : Long {
        if (shouldThrowException) {
            throw Exception("some exception")
        }

        return if (splitEntities.containsKey(splitEntity.splitId)) {
            -1
        }
        else {
            splitEntities[splitEntity.splitId] = splitEntity
            splitEntity.splitId.toLong()
        }
    }

    override suspend fun deleteSplit(splitEntity : SplitEntity) : Int {
        if (shouldThrowException) {
            throw Exception("some exception")
        }

        return if (splitEntities.remove(splitEntity.splitId) == null) {
            0
        } else {
            1
        }
    }

    override suspend fun updateSplit(splitEntity : SplitEntity) : Int {
        if (shouldThrowException) {
            throw Exception("some exception")
        }

        return if (splitEntities.containsKey(splitEntity.splitId)) {
            splitEntities[splitEntity.splitId] = splitEntity
            1
        }
        else {
            0
        }
    }

}
