package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.domain.fakes.FakeSplitRepository
import ca.tremblay95.billsplit.domain.model.Split
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class AddSplitImplTests {

    lateinit var splitRepository : FakeSplitRepository
    lateinit var addSplit : AddSplitImpl

    @Before
    fun setUp() {
        splitRepository = FakeSplitRepository()
        addSplit = AddSplitImpl(splitRepository)
    }

    @Test
    fun addSplitInvoke_newSplit_returnsResultSuccess() = runTest {
        val split = Split(1, "test_split", "this is a test split")

        addSplit(split)

        val actual = splitRepository.splits.first()

        assertThat(actual).isEqualTo(split)
    }

    @Test
    fun addSplitInvoke_duplicateSplit_returnsResultError() = runTest {
        val split = Split(1, "test_split", "this is a test split")
        splitRepository.splits = mutableListOf(split)

        try {
            val actual = addSplit(split)

            assert(actual is Result.Error)
        } catch (e : Exception) {
            fail("Not handled gracefully")
        }
    }
}
