package ca.tremblay95.billsplit.data.mappers

import ca.tremblay95.billsplit.data.model.OperandEntity
import ca.tremblay95.billsplit.domain.model.Operand

fun OperandEntity.toOperand() : Operand =
    Operand(value = value, name = name)
