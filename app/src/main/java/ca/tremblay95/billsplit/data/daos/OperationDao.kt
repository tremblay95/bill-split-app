package ca.tremblay95.billsplit.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ca.tremblay95.billsplit.data.models.SplitOperation
import ca.tremblay95.billsplit.data.relations.OperationWithOperands
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {
    @Transaction
    @Query("SELECT * FROM split_operation WHERE operation_id = :id")
    fun getOperationWithOperands(id : Int) : Flow<OperationWithOperands?>

    @Query("SELECT * FROM split_operation WHERE parent_id = :parentId")
    fun getChildOperations(parentId : Int) : Flow<List<SplitOperation>>

    @Transaction
    @Query("SELECT * FROM split_operation WHERE operation_id IN (:ids)")
    fun getOperationsWithOperands(ids : List<Int?>) : Flow<Array<OperationWithOperands?>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSplitOperation(operation : SplitOperation)

    @Delete
    suspend fun deleteSplitOperation(operation : SplitOperation)

    @Update
    suspend fun updateSplitOperation(operation : SplitOperation)
}
