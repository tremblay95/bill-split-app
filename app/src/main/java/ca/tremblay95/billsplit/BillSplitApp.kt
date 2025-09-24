package ca.tremblay95.billsplit

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ca.tremblay95.billsplit.presentation.ui.navigation.BillSplitNavHost

@Composable
fun BillSplitApp(navController : NavHostController = rememberNavController()) {
    BillSplitNavHost(navController = navController)
}
