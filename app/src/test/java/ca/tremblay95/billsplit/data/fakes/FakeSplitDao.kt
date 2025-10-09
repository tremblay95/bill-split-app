package ca.tremblay95.billsplit.data.fakes

import ca.tremblay95.billsplit.data.data_source.SplitDao
import ca.tremblay95.billsplit.data.model.SplitEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSplitDao : SplitDao {
    private val splitEntities = mutableMapOf<Int, SplitEntity>()

    override fun getAllSplits() : Flow<List<SplitEntity>> = flow {
        emit(splitEntities.values.toList())
    }

    override fun getSplit(splitId : Int) : Flow<SplitEntity?> = flow {
        emit(splitEntities[splitId])
    }

    override suspend fun insertSplit(splitEntity : SplitEntity) : Long { //-1
        return if (splitEntities.containsKey(splitEntity.splitId)) {
            -1
        }
        else {
            splitEntities[splitEntity.splitId] = splitEntity
            splitEntity.splitId.toLong()
        }
    }

    override suspend fun deleteSplit(splitEntity : SplitEntity) : Int { //0
        return if (splitEntities.remove(splitEntity.splitId) == null) 0 else 1
    }

    override suspend fun updateSplit(splitEntity : SplitEntity) : Int { //0
        return if (splitEntities.containsKey(splitEntity.splitId)) {
            splitEntities[splitEntity.splitId] = splitEntity
            1
        }
        else {
            0
        }
    }

}
