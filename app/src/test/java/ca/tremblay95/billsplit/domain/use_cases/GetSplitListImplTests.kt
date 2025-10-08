package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.data.model.SplitEntity
import ca.tremblay95.billsplit.domain.fakes.FakeSplitRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
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
    fun getSplitList_listEmpty_emptyListReturned() = runTest {

        val actual = splitRepository.getAllSplits().first()

        assertThat(actual).isEmpty()
    }

    @Test
    fun getSplitList_listNotEmpty_allEntriesReturned() = runTest {
        splitRepository.splitEntities = mutableListOf<SplitEntity>(
            SplitEntity(1, "1", ""),
            SplitEntity(2, "2", ""),
            SplitEntity(3, "3", "")
        )

        val actual = splitRepository.getAllSplits().first()

        assertThat(actual.count()).isEqualTo(splitRepository.splitEntities.count())

        actual.zip(splitRepository.splitEntities).forEach { (actual, expected) ->
            assertThat(actual).isEqualTo(expected)
        }
    }
}
