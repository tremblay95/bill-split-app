package ca.tremblay95.billsplit.presentation.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ca.tremblay95.billsplit.BillSplitApplication
import ca.tremblay95.billsplit.presentation.create_split.CreateSplitViewModel
import ca.tremblay95.billsplit.presentation.split_list.SplitListViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for HomeViewModel
        initializer {
            SplitListViewModel(billSplitApplication().container.splitsRepository)
        }

        // Initializer for NewSplitMethodViewModel
        initializer {
            CreateSplitViewModel(billSplitApplication().container.splitsRepository)
        }
    }
}

fun CreationExtras.billSplitApplication() : BillSplitApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BillSplitApplication)
