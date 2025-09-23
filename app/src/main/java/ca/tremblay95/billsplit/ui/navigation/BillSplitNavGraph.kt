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
import ca.tremblay95.billsplit.ui.split.NewSplitMethodDestination
import ca.tremblay95.billsplit.ui.split.NewSplitMethodScreen
import ca.tremblay95.billsplit.ui.split.SplitMethodDetailsDestination
import ca.tremblay95.billsplit.ui.split.SplitMethodDetailsScreen

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
                    navController.navigate("${SplitMethodDetailsDestination.route}/${it}")
                }
            )
        }
        composable(NewSplitMethodDestination.route) {
            NewSplitMethodScreen(
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
