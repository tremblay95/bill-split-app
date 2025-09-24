package ca.tremblay95.billsplit.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import ca.tremblay95.billsplit.data.model.OperandEntity

@Dao
interface OperandDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSplitOperand(operand : OperandEntity)

    @Delete
    suspend fun deleteSplitOperand(operand: OperandEntity)

    @Update
    suspend fun updateSplitOperand(operand: OperandEntity)
}
