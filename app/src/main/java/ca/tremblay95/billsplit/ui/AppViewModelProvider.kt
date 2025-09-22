package ca.tremblay95.billsplit.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ca.tremblay95.billsplit.BillSplitApplication
import ca.tremblay95.billsplit.ui.home.HomeViewModel
import ca.tremblay95.billsplit.ui.split.NewSplitMethodViewModel
import ca.tremblay95.billsplit.ui.split.SplitMethodDetailsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(billSplitApplication().container.splitsRepository)
        }

        // Initializer for NewSplitMethodViewModel
        initializer {
            NewSplitMethodViewModel(billSplitApplication().container.splitsRepository)
        }

        // Initializer for SplitMethodDetailsViewModel
        initializer {
            SplitMethodDetailsViewModel(
                this.createSavedStateHandle(),
                billSplitApplication().container.splitsRepository
            )
        }
    }
}

fun CreationExtras.billSplitApplication() : BillSplitApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BillSplitApplication)
