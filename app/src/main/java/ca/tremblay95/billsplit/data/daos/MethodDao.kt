package ca.tremblay95.billsplit.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ca.tremblay95.billsplit.data.models.SplitMethod
import ca.tremblay95.billsplit.data.relations.MethodWithOperationsAndOperands

@Dao
interface MethodDao {
    @Query("SELECT * FROM split_method ORDER BY method_id ASC")
    fun getAllSplitMethods() : List<SplitMethod>

    @Transaction
    @Query("SELECT * FROM split_method WHERE method_id = :methodId")
    fun getMethodWithOperationsAndOperands(methodId : Int) : MethodWithOperationsAndOperands

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertSplitMethod(method : SplitMethod)

    @Delete
    fun deleteSplitMethod(method : SplitMethod)

    @Update
    fun updateSplitMethod(method : SplitMethod)
}
