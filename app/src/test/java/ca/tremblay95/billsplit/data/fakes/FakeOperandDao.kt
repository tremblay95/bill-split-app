package ca.tremblay95.billsplit.data.fakes

import ca.tremblay95.billsplit.data.data_source.OperandDao
import ca.tremblay95.billsplit.data.model.OperandEntity

class FakeOperandDao : OperandDao {
    override suspend fun insertOperand(operandEntity : OperandEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOperand(operandEntity : OperandEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOperand(operandEntity : OperandEntity) {
        TODO("Not yet implemented")
    }

}
