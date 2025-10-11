package ca.tremblay95.billsplit.presentation.split_list

import app.cash.turbine.test
import ca.tremblay95.billsplit.R
import ca.tremblay95.billsplit.common.BillSplitError
import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.domain.model.Split
import ca.tremblay95.billsplit.presentation.fakes.FakeGetSplitList
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.Test

class SplitListViewModelTests {

    @Test
    fun splitListViewModel_getSplitListReturnsSuccess_uiStateUpdatedCorrectly() = runTest {
        val splits = listOf(Split(1, "1", "1"))
        val getSplitList = FakeGetSplitList(Result.Success(splits))
        val splitListViewModel = SplitListViewModel(getSplitList)

        splitListViewModel.uiState.test {
            val initial = awaitItem()
            assertThat(initial).isEqualTo(SplitListState(isLoading = true))

            val actual = awaitItem()
            assertThat(actual).isEqualTo(SplitListState(splitList = splits))

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun splitListViewModel_getSplitListReturnsUnknownError_uiStateUpdatedCorrectly() = runTest {
        val getSplitList = FakeGetSplitList(Result.Error(BillSplitError.Unknown("some error")))
        val splitListViewModel = SplitListViewModel(getSplitList)

        splitListViewModel.uiState.test {
            val initial = awaitItem()
            assertThat(initial).isEqualTo(SplitListState(isLoading = true))

            val actual = awaitItem()
            assertThat(actual.errorStringResource).isEqualTo(R.string.split_list_retrieval_error)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun splitListViewModel_getSplitListReturnsNotFound_uiStateUpdatedCorrectly() = runTest {
        val getSplitList = FakeGetSplitList(Result.NotFound)
        val splitListViewModel = SplitListViewModel(getSplitList)

        splitListViewModel.uiState.test {
            val initial = awaitItem()
            assertThat(initial).isEqualTo(SplitListState(isLoading = true))

            val actual = awaitItem()
            assertThat(actual).isEqualTo(SplitListState())

            cancelAndConsumeRemainingEvents()
        }
    }
}
