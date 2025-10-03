package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.data.mappers.toSplit
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.repository.SplitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSplit(
    private val splitRepository : SplitRepository
) {
    operator fun invoke(id : Int) : Flow<Split> =
        splitRepository.getSplit(id).map { splitEntity ->
            splitEntity.toSplit()
        }
}
