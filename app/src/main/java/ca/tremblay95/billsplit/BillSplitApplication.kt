package ca.tremblay95.billsplit

import android.app.Application
import ca.tremblay95.billsplit.di.AppModule
import ca.tremblay95.billsplit.di.AppModuleImpl

class BillSplitApplication : Application() {
    lateinit var appModule : AppModule

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}
