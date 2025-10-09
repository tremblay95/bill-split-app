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
    suspend fun insertOperand(operandEntity : OperandEntity)

    @Delete
    suspend fun deleteOperand(operandEntity: OperandEntity)

    @Update
    suspend fun updateOperand(operandEntity: OperandEntity)
}
