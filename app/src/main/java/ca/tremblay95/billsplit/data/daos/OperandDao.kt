package ca.tremblay95.billsplit.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import ca.tremblay95.billsplit.data.models.SplitOperand

@Dao
interface OperandDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSplitOperand(operand : SplitOperand)

    @Delete
    suspend fun deleteSplitOperand(operand: SplitOperand)

    @Update
    suspend fun updateSplitOperand(operand: SplitOperand)
}
