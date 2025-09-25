package ca.tremblay95.billsplit.di

import android.content.Context
import ca.tremblay95.billsplit.data.repository.OfflineSplitRepository
import ca.tremblay95.billsplit.data.data_source.SplitDatabase
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import ca.tremblay95.billsplit.domain.use_cases.GetSplitListUseCase

interface AppModule {
    val splitsRepository : SplitRepository
    val getSplitListUseCase : GetSplitListUseCase
}

class AppModuleImpl(context : Context) : AppModule {
    override val splitsRepository : SplitRepository by lazy {
        val db = SplitDatabase.Companion.getDatabase(context)
        OfflineSplitRepository(db.methodDao(), db.operationDao(), db.operandDao())
    }

    override val getSplitListUseCase : GetSplitListUseCase by lazy {
        GetSplitListUseCase(splitsRepository)
    }
}
