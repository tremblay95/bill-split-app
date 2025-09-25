package ca.tremblay95.billsplit.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity
import ca.tremblay95.billsplit.data.model.SplitEntity

@Database(entities = [SplitEntity::class, OperationEntity::class, OperandEntity::class], version = 1, exportSchema = false)
abstract class SplitDatabase : RoomDatabase() {
    abstract fun splitDao() : SplitDao
    abstract fun operationDao() : OperationDao
    abstract fun operandDao() : OperandDao

    companion object {
        @Volatile
        private var Instance : SplitDatabase? = null

        fun getDatabase(context : Context) : SplitDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, SplitDatabase::class.java, "split_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
