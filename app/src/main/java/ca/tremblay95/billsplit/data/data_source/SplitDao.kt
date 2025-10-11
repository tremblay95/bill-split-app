package ca.tremblay95.billsplit.data.data_source

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
    fun getAllSplits() : Flow<List<SplitEntity>>

    @Query("SELECT * FROM split WHERE split_id = :splitId")
    fun getSplit(splitId : Int) : Flow<SplitEntity?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSplit(splitEntity : SplitEntity) : Long

    @Delete
    suspend fun deleteSplit(splitEntity : SplitEntity) : Int

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun updateSplit(splitEntity : SplitEntity) : Int
}
