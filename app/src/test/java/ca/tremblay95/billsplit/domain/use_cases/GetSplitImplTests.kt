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

class GetSplitImplTests {

    lateinit var splitRepository : FakeSplitRepository
    lateinit var getSplit : GetSplitImpl

    @Before
    fun setUp() {
        splitRepository = FakeSplitRepository()
        getSplit = GetSplitImpl(splitRepository)
    }

    @Test
    fun getSplitInvoke_validSplitId_returnsCorrectEntry_Success() = runTest {
        val split = Split(1, "test_entity", "this is a test entity")
        splitRepository.splits = mutableListOf(split)

        val actual = getSplit(split.id).first()

        if (actual is Result.Success<Split>) {
            assertThat(actual.data).isEqualTo(split)
        }
        else {
            fail("Expected Result.Success")
        }
    }

    @Test
    fun getSplitInvoke_badSplitId_returnsNotFound() = runTest {
        val actual = getSplit(420).first()

        if (actual !is Result.NotFound) {
            fail("Expected Result.NotFound")
        }
    }
}
