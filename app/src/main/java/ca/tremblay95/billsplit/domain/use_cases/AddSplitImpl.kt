package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.repository.SplitRepository

class AddSplitImpl(
    private val repository : SplitRepository
) : AddSplit {
    override suspend operator fun invoke(split : Split) : Result<Unit> = repository.insertSplit(split)
}
