package ca.tremblay95.billsplit.common

sealed class BillSplitError {
    object DuplicateName : BillSplitError()
    data class Unknown(val message : String?) : BillSplitError()
}
