package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import kotlinx.coroutines.flow.Flow

class GetSplitImpl(
    private val splitRepository : SplitRepository
) : GetSplit {
    override operator fun invoke(id : Int) : Flow<Result<Split>> = splitRepository.getSplit(id)
}
