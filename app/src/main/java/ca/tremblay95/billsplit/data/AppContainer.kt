package ca.tremblay95.billsplit.data

import android.content.Context

interface AppContainer {
    val splitsRepository : SplitsRepository
}

class AppDataContainer(context : Context) : AppContainer {
    override val splitsRepository : SplitsRepository by lazy {
        val db = SplitDatabase.getDatabase(context)
        OfflineSplitsRepository(db.methodDao(), db.operationDao(), db.operandDao())
    }
}
