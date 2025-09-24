package ca.tremblay95.billsplit.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ca.tremblay95.billsplit.presentation.split_list.SplitListScreen
import ca.tremblay95.billsplit.presentation.split_list.SplitListDestination
import ca.tremblay95.billsplit.presentation.create_split.CreateSplitDestination
import ca.tremblay95.billsplit.presentation.create_split.CreateSplitScreen

@Composable
fun BillSplitNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SplitListDestination.route,
        modifier = modifier
    ) {
        composable(SplitListDestination.route) {
            SplitListScreen(
                navigateToCreateSplitScreen = {
                    navController.navigate(CreateSplitDestination.route)
                },
                navigateToSplitDetailsScreen = {
//                    navController.navigate("") // TODO: SplitDetailsScreen.route
                }
            )
        }
        composable(CreateSplitDestination.route) {
            CreateSplitScreen(
                navigateToEditSplitMethod = {},
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
