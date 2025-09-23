package ca.tremblay95.billsplit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.tremblay95.billsplit.ui.home.HomeScreen
import ca.tremblay95.billsplit.ui.home.HomeScreenDestination
import ca.tremblay95.billsplit.ui.splitmethod.create.SplitMethodCreateDestination
import ca.tremblay95.billsplit.ui.splitmethod.create.SplitMethodCreateScreen
import ca.tremblay95.billsplit.ui.splitmethod.details.SplitMethodDetailsDestination
import ca.tremblay95.billsplit.ui.splitmethod.details.SplitMethodDetailsScreen

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
                    navController.navigate(SplitMethodCreateDestination.route)
                },
                navigateToSplitDetailsScreen = {
                    navController.navigate("${SplitMethodDetailsDestination.route}/${it}")
                }
            )
        }
        composable(SplitMethodCreateDestination.route) {
            SplitMethodCreateScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = SplitMethodDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(SplitMethodDetailsDestination.methodIdArg) {
                type = NavType.IntType
            })
        ) {
            SplitMethodDetailsScreen()
        }
    }
}
