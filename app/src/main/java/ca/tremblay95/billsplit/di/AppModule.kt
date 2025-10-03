package ca.tremblay95.billsplit.di

import android.content.Context
import ca.tremblay95.billsplit.data.data_source.SplitDatabase
import ca.tremblay95.billsplit.data.repository.OfflineSplitRepository
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import ca.tremblay95.billsplit.domain.use_cases.AddSplit
import ca.tremblay95.billsplit.domain.use_cases.GetSplitList

interface AppModule {
    val splitRepository : SplitRepository
    val splitUseCases : SplitUseCases
}

class AppModuleImpl(context : Context) : AppModule {
    override val splitRepository : SplitRepository by lazy {
        val db = SplitDatabase.Companion.getDatabase(context)
        OfflineSplitRepository(db.splitDao(), db.operationDao(), db.operandDao())
    }

    override val splitUseCases : SplitUseCases by lazy {
        SplitUseCases(
            getSplitList = GetSplitList(splitRepository),
            addSplit = AddSplit(splitRepository)
        )
    }

}
