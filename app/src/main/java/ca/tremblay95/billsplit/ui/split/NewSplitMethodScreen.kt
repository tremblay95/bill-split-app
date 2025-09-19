package ca.tremblay95.billsplit.ui.split

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.tremblay95.billsplit.R
import ca.tremblay95.billsplit.ui.AppViewModelProvider
import ca.tremblay95.billsplit.ui.BillSplitTopBar
import ca.tremblay95.billsplit.ui.model.SplitMethodDetails
import ca.tremblay95.billsplit.ui.navigation.NavigationDestination
import ca.tremblay95.billsplit.ui.theme.BillSplitAppTheme
import kotlinx.coroutines.launch

object NewSplitMethodDestination : NavigationDestination {
    override val route : String = "new_method"
    override val titleResource : Int = R.string.new_method_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewSplitMethodScreen(
    navigateToEditSplitMethod : () -> Unit,
    onNavigateUp : () -> Unit,
    canNavigateBack : Boolean = true,
    viewModel : NewSplitMethodViewModel = viewModel(factory = AppViewModelProvider.Factory) // TODO: use viewmodel factory
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BillSplitTopBar(
                title = stringResource(NewSplitMethodDestination.titleResource),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        NewSplitMethodBody(
            methodUiState = viewModel.methodUiState,
            onMethodValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveSplitMethod()
                    navigateToEditSplitMethod()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun NewSplitMethodBody(
    methodUiState : MethodUiState,
    onMethodValueChange : (SplitMethodDetails) -> Unit,
    onSaveClick : () -> Unit,
    modifier : Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
    ) {
        MethodInputForm(
            methodDetails = methodUiState.methodDetails,
            onValueChange = onMethodValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = methodUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun MethodInputForm(
    methodDetails : SplitMethodDetails,
    onValueChange : (SplitMethodDetails) -> Unit = {},
    enabled : Boolean = true,
    modifier : Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = methodDetails.name,
            onValueChange = {
                onValueChange(methodDetails.copy(name = it))
            },
            label = {
                Text(stringResource(R.string.name_required))
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = methodDetails.description,
            onValueChange = {
                onValueChange(methodDetails.copy(description = it))
            },
            label = {
                Text(stringResource(R.string.description))
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            maxLines = 5
        )
        if (enabled) {
            Text(
                text = stringResource(R.string.required_fields),
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewSplitMethodPreview() {
    BillSplitAppTheme {
        NewSplitMethodBody(
            methodUiState = MethodUiState(
                SplitMethodDetails(
                    name = "Split Preview",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta dignissim quam vitae imperdiet. Mauris vestibulum quam ut neque venenatis, sed mattis odio gravida. Vivamus iaculis dictum tortor, accumsan mattis mauris fermentum sodales. Curabitur feugiat est id venenatis posuere. Cras eget hendrerit mauris, tempus semper quam. Fusce iaculis vehicula ex sit amet placerat."
                )
            ),
            onMethodValueChange = {},
            onSaveClick = {}
        )
    }
}
