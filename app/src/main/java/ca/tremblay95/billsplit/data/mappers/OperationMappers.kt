package ca.tremblay95.billsplit.data.mappers

import ca.tremblay95.billsplit.data.relation.OperationWithOperands
import ca.tremblay95.billsplit.domain.model.DivideOperation
import ca.tremblay95.billsplit.domain.model.Operation
import ca.tremblay95.billsplit.domain.model.OperationType
import ca.tremblay95.billsplit.domain.model.SubtractOperation

fun OperationWithOperands.toOperation() : Operation =
    when(operation.operationType) {
        OperationType.Divide -> DivideOperation(operands.map { operand -> operand.toOperand() })
        OperationType.Subtract -> SubtractOperation(operands.map { operand -> operand.toOperand() })
    }
