package ca.tremblay95.billsplit.ui.split

import ca.tremblay95.billsplit.R
import ca.tremblay95.billsplit.ui.navigation.NavigationDestination

object SplitMethodDetailsDestination : NavigationDestination {
    override val route : String = "method_details"
    override val titleResource : Int = R.string.method_details_title
    const val methodIdArg = "methodId"
    val routeWithArgs = "$route/{$methodIdArg}"
}
