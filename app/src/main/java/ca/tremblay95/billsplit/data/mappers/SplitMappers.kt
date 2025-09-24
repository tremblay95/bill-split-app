package ca.tremblay95.billsplit.data.mappers

import ca.tremblay95.billsplit.data.model.SplitEntity
import ca.tremblay95.billsplit.domain.model.Split

// TODO: extension functions for going between the ui model and the data model
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
