package ca.tremblay95.billsplit.presentation.fakes

import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.use_cases.GetSplitList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetSplitList(private val result : Result<List<Split>>) : GetSplitList {
    override fun invoke() : Flow<Result<List<Split>>> = flow {
        emit(Result.Loading)
        emit(result)
    }

}
