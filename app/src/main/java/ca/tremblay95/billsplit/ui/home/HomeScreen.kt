package ca.tremblay95.billsplit.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tremblay95.billsplit.R
import ca.tremblay95.billsplit.ui.BillSplitTopBar
import ca.tremblay95.billsplit.ui.navigation.NavigationDestination
import ca.tremblay95.billsplit.ui.theme.BillSplitAppTheme

object HomeScreenDestination : NavigationDestination {
    override val route: String = "home"
    override val titleResource: Int = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToCreateSplitScreen: () -> Unit,
    navigateToSplitDetailsScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel : HomeViewModel = viewModel()
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            BillSplitTopBar(
                title = stringResource(HomeScreenDestination.titleResource),
                canNavigateBack = false,
                scrollBehaviour = scrollBehaviour
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    TODO("Not yet implemented")
                },
                shape = MaterialTheme.shapes.medium,
                modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.create_split_title)
                )
            }
        }
    ) { innerPadding ->
        HomeBody(
            splitList = listOf(),
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding
        )
    }
}

@Composable
fun HomeBody(
    splitList: List<String>, // TODO: replace string with the SplitSchema class
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (splitList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_splits_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding)
            )
        } else {
            TODO("Not yet implemented")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    BillSplitAppTheme {
        HomeBody(listOf(), Modifier.fillMaxSize())
    }
}
