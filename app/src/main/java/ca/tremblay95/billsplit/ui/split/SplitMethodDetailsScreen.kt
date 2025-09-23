package ca.tremblay95.billsplit.ui.split

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tremblay95.billsplit.R
import ca.tremblay95.billsplit.ui.AppViewModelProvider
import ca.tremblay95.billsplit.ui.navigation.NavigationDestination

object SplitMethodDetailsDestination : NavigationDestination {
    override val route : String = "method_details"
    override val titleResource : Int = R.string.method_details_title
    const val methodIdArg = "methodId"
    val routeWithArgs = "$route/{$methodIdArg}"
}

@Composable
fun SplitMethodDetailsScreen(
    viewModel : SplitMethodDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()

    Column {
        Text(uiState.value.name)
        Text(uiState.value.description)
        if (uiState.value.splitOperation != null) {
            Text(uiState.value.splitOperation!!.splitType.name)
        }
    }
}
