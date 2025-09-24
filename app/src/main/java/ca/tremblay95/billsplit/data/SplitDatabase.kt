package ca.tremblay95.billsplit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.tremblay95.billsplit.data.dao.SplitDao
import ca.tremblay95.billsplit.data.dao.OperandDao
import ca.tremblay95.billsplit.data.dao.OperationDao
import ca.tremblay95.billsplit.data.model.SplitEntity
import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.data.model.OperationEntity

@Database(entities = [SplitEntity::class, OperationEntity::class, OperandEntity::class], version = 1, exportSchema = false)
abstract class SplitDatabase : RoomDatabase() {
    abstract fun methodDao() : SplitDao
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
