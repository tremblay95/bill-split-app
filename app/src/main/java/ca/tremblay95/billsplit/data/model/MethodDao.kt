package ca.tremblay95.billsplit.data.model

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ca.tremblay95.billsplit.data.model.relations.MethodWithOperationsAndOperands

@Dao
interface MethodDao {
    @Transaction
    @Query("SELECT * FROM split_method")
    fun getMethodsWithOperationsAndOperands() : List<MethodWithOperationsAndOperands>


}
