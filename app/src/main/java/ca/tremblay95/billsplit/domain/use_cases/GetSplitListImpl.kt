package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import kotlinx.coroutines.flow.Flow

class GetSplitListImpl(
    private val repository : SplitRepository
) : GetSplitList {
    override operator fun invoke() : Flow<Result<List<Split>>> = repository.getAllSplits()
}
