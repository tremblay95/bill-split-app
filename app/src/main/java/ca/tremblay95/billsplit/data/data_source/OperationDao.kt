package ca.tremblay95.billsplit.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.data.relation.OperationWithOperands
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {
    @Transaction
    @Query("SELECT * FROM operation WHERE operation_id = :id")
    fun getOperationWithOperands(id : Int) : Flow<OperationWithOperands?>

    @Query("SELECT * FROM operation WHERE parent_id = :parentId")
    fun getChildOperations(parentId : Int) : Flow<List<OperationEntity>>

    @Transaction
    @Query("SELECT * FROM operation WHERE operation_id IN (:ids)")
    fun getOperationsWithOperands(ids : List<Int?>) : Flow<Array<OperationWithOperands>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertOperation(operation : OperationEntity)

    @Delete
    suspend fun deleteOperation(operation : OperationEntity)

    @Update
    suspend fun updateOperation(operation : OperationEntity)
}
