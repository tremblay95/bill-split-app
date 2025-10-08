package ca.tremblay95.billsplit.common

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data : T) : Result<T>()
    object NotFound : Result<Nothing>()
    data class Error(val message : String, val cause : Throwable? = null) : Result<Nothing>()
}
