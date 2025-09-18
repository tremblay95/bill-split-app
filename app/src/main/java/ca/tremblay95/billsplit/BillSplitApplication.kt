package ca.tremblay95.billsplit

import android.app.Application
import ca.tremblay95.billsplit.data.AppContainer
import ca.tremblay95.billsplit.data.AppDataContainer

class BillSplitApplication : Application() {
    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
