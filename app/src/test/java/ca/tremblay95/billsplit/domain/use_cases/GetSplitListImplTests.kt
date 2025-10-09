package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.domain.fakes.FakeSplitRepository
import ca.tremblay95.billsplit.domain.model.Split
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class GetSplitListImplTests {

    lateinit var splitRepository : FakeSplitRepository
    lateinit var getSplitList : GetSplitListImpl

    @Before
    fun setUp() {
        splitRepository = FakeSplitRepository()
        getSplitList = GetSplitListImpl(splitRepository)
    }

    @Test
    fun getSplitListInvoke_listEmpty_returnsEmptyList_Success() = runTest {

        val actual = splitRepository.getAllSplits().first()

        if (actual is Result.Success<List<Split>>) {
            assertThat(actual.data).isEmpty()
        }
        else {
            fail("Expected Result.Success")
        }
    }

    @Test
    fun getSplitListInvoke_listNotEmpty_returnsCompleteList_Success() = runTest {
        splitRepository.splits[1] = Split(1, "1", "")
        splitRepository.splits[2] = Split(2, "2", "")
        splitRepository.splits[3] = Split(3, "3", "")


        val actual = splitRepository.getAllSplits().first()

        if (actual is Result.Success<List<Split>>) {
            assertThat(actual.data.count()).isEqualTo(splitRepository.splits.count())

            actual.data.zip(splitRepository.splits.values).forEach { (actual, expected) ->
                assertThat(actual).isEqualTo(expected)
            }
        }
        else {
            fail("Expected Result.Success")
        }
    }
}
