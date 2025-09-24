package ca.tremblay95.billsplit.di

import android.content.Context
import ca.tremblay95.billsplit.data.OfflineSplitsRepository
import ca.tremblay95.billsplit.data.SplitDatabase
import ca.tremblay95.billsplit.data.SplitsRepository

interface AppModule {
    val splitsRepository : SplitsRepository
}

class AppModuleImpl(context : Context) : AppModule {
    override val splitsRepository : SplitsRepository by lazy {
        val db = SplitDatabase.Companion.getDatabase(context)
        OfflineSplitsRepository(db.methodDao(), db.operationDao(), db.operandDao())
    }
}
