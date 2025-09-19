package ca.tremblay95.billsplit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ca.tremblay95.billsplit.ui.home.HomeScreen
import ca.tremblay95.billsplit.ui.home.HomeScreenDestination
import ca.tremblay95.billsplit.ui.split.NewSplitMethodDestination
import ca.tremblay95.billsplit.ui.split.NewSplitMethodScreen

@Composable
fun BillSplitNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
        modifier = modifier
    ) {
        composable(HomeScreenDestination.route) {
            HomeScreen(
                navigateToCreateSplitScreen = {
                    navController.navigate(NewSplitMethodDestination.route)
                },
                navigateToSplitDetailsScreen = {
//                    navController.navigate("") // TODO: SplitDetailsScreen.route
                }
            )
        }
        composable(NewSplitMethodDestination.route) {
            NewSplitMethodScreen(
                navigateToEditSplitMethod = {},
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
