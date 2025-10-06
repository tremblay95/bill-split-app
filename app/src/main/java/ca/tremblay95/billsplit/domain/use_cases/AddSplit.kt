package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.domain.model.Split

interface AddSplit {
    suspend operator fun invoke(split : Split)
}
