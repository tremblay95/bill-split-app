package ca.tremblay95.billsplit.domain.use_cases

import ca.tremblay95.billsplit.data.mappers.toSplit
import ca.tremblay95.billsplit.data.model.SplitEntity
import ca.tremblay95.billsplit.domain.fakes.FakeSplitRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
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
    fun getSplit_validSplitId_correctSplitEntityReturned() = runTest {
        val splitEntity = SplitEntity(1, "test_entity", "this is a test entity")
        splitRepository.splitEntities = mutableListOf(splitEntity)

        val actual = getSplit(splitEntity.splitId).first()

        assertThat(actual).isEqualTo(splitEntity.toSplit())
    }

    @Test
    fun getSplit_invalidSplitId_handledCorrectly() {

    }
}
