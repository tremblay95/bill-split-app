package ca.tremblay95.billsplit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.tremblay95.billsplit.data.daos.MethodDao
import ca.tremblay95.billsplit.data.daos.OperandDao
import ca.tremblay95.billsplit.data.daos.OperationDao
import ca.tremblay95.billsplit.data.models.SplitMethod
import ca.tremblay95.billsplit.data.models.SplitOperand
import ca.tremblay95.billsplit.data.models.SplitOperation

@Database(entities = [SplitMethod::class, SplitOperation::class, SplitOperand::class], version = 1, exportSchema = false)
abstract class SplitDatabase : RoomDatabase() {
    abstract fun methodDao() : MethodDao
    abstract fun operationDao() : OperationDao
    abstract fun operandDao() : OperandDao

    companion object {
        @Volatile
        private var Instance : SplitDatabase? = null

        fun getDatabase(context : Context) : SplitDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, SplitDatabase::class.java, "split_database")
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
