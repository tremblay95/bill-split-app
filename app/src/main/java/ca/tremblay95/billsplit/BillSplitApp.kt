package ca.tremblay95.billsplit

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ca.tremblay95.billsplit.presentation.ui.navigation.BillSplitNavHost

@Composable
fun BillSplitApp(navController : NavHostController = rememberNavController()) {
    BillSplitNavHost(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillSplitTopBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier.Companion,
    scrollBehaviour: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        modifier = modifier,
        scrollBehavior = scrollBehaviour,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
