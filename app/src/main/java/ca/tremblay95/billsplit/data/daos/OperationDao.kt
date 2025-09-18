package ca.tremblay95.billsplit.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import ca.tremblay95.billsplit.data.models.SplitOperation

@Dao
interface OperationDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertSplitOperation(operation : SplitOperation)

    @Delete
    fun deleteSplitOperation(operation : SplitOperation)

    @Update
    fun updateSplitOperation(operation : SplitOperation)
}
