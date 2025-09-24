package ca.tremblay95.billsplit.di

import android.content.Context
import ca.tremblay95.billsplit.data.repository.OfflineSplitRepository
import ca.tremblay95.billsplit.data.data_source.SplitDatabase
import ca.tremblay95.billsplit.domain.repository.SplitRepository

interface AppModule {
    val splitsRepository : SplitRepository
}

class AppModuleImpl(context : Context) : AppModule {
    override val splitsRepository : SplitRepository by lazy {
        val db = SplitDatabase.Companion.getDatabase(context)
        OfflineSplitRepository(db.methodDao(), db.operationDao(), db.operandDao())
    }
}
