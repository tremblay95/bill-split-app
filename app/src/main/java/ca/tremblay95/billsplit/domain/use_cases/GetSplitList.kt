package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.data.mappers.toSplit
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSplitList(
    private val repository : SplitRepository
) {
    operator fun invoke() : Flow<List<Split>> =
        repository.getAllSplits().map { splitEntityList ->
            splitEntityList.map { splitEntity -> splitEntity.toSplit() }
        }
}
