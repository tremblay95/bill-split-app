package ca.tremblay95.billsplit.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ca.tremblay95.billsplit.data.model.SplitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SplitDao {
    @Query("SELECT * FROM split ORDER BY split_id ASC")
    fun getAllSplitMethods() : Flow<List<SplitEntity>>

    @Query("SELECT * FROM split WHERE split_id = :splitId")
    fun getSplitMethod(splitId : Int) : Flow<SplitEntity?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSplitMethod(method : SplitEntity)

    @Delete
    suspend fun deleteSplitMethod(method : SplitEntity)

    @Update
    suspend fun updateSplitMethod(method : SplitEntity)
}
