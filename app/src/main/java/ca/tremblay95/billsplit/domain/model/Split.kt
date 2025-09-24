package ca.tremblay95.billsplit.domain.model

data class Split(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val operation: Operation? = null
)
