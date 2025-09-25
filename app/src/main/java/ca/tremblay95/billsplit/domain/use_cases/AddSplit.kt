package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.data.mappers.toSplitEntity
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.domain.repository.SplitRepository

class AddSplit(
    private val repository : SplitRepository
) {
    suspend operator fun invoke(split : Split) {
        repository.insertSplit(split.toSplitEntity())
    }
}
