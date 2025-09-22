package ca.tremblay95.billsplit.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ca.tremblay95.billsplit.data.models.SplitMethod
import kotlinx.coroutines.flow.Flow

@Dao
interface MethodDao {
    @Query("SELECT * FROM split_method ORDER BY method_id ASC")
    fun getAllSplitMethods() : Flow<List<SplitMethod>>

    @Query("SELECT * FROM split_method WHERE method_id = :methodId")
    fun getSplitMethod(methodId : Int) : Flow<SplitMethod?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSplitMethod(method : SplitMethod)

    @Delete
    suspend fun deleteSplitMethod(method : SplitMethod)

    @Update
    suspend fun updateSplitMethod(method : SplitMethod)
}
