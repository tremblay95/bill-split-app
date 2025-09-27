package ca.tremblay95.billsplit.di

import ca.tremblay95.billsplit.domain.use_cases.AddSplit
import ca.tremblay95.billsplit.domain.use_cases.GetSplitList

data class SplitUseCases(
    val getSplitList : GetSplitList,
    val addSplit : AddSplit
)
