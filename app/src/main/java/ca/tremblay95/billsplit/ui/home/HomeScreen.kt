package ca.tremblay95.billsplit.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tremblay95.billsplit.R
import ca.tremblay95.billsplit.data.SplitSchema
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
            onSplitClick = navigateToSplitDetailsScreen,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding
        )
    }
}

@Composable
fun HomeBody(
    splitList: List<SplitSchema>,
    onSplitClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (splitList.isEmpty()) Arrangement.Center else Arrangement.Top,
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
            SplitList(
                splitList = splitList,
                onSplitClick = { onSplitClick(it.id) },
                contentPadding = contentPadding,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun SplitList(
    splitList: List<SplitSchema>,
    onSplitClick: (SplitSchema) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(items = splitList) { split ->
            SplitEntry(
                split = split,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .clickable {
                        onSplitClick(split)
                    }
            )
        }
    }
}

@Composable
fun SplitEntry(
    split: SplitSchema,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_large))
                .fillMaxWidth()
        ) {
            Text(
                text = split.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Left
            )
            Text(
                text = split.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = integerResource(R.integer.split_list_description_max_lines),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    BillSplitAppTheme {
        HomeBody(listOf(
            SplitSchema(0, "Test Split 1", "description 1"),
            SplitSchema(1, "Test Split 2", "description 2. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."),
            SplitSchema(2, "Test Split 3", "description 3")
        ), {}, Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    BillSplitAppTheme {
        HomeBody(listOf(), {}, Modifier.fillMaxSize())
    }
}
