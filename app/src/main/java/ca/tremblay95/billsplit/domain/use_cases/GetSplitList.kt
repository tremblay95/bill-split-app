package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.domain.model.Split
import kotlinx.coroutines.flow.Flow

interface GetSplitList {
    operator fun invoke() : Flow<Result<List<Split>>>
}
