package ca.tremblay95.billsplit.data.mappers

import ca.tremblay95.billsplit.data.model.SplitEntity
import ca.tremblay95.billsplit.domain.model.Split

fun Split.toSplitEntity() : SplitEntity =
    SplitEntity(
        splitId = id,
        name = name,
        description = description
    )

fun SplitEntity.toSplit() : Split =
    Split(
        id = splitId,
        name = name,
        description = description
    )
