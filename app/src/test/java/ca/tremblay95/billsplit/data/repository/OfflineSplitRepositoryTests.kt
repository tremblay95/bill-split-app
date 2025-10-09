package ca.tremblay95.billsplit.data.repository

import app.cash.turbine.test
import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.data.fakes.FakeOperandDao
import ca.tremblay95.billsplit.data.fakes.FakeOperationDao
import ca.tremblay95.billsplit.data.fakes.FakeSplitDao
import ca.tremblay95.billsplit.data.mappers.toSplit
import ca.tremblay95.billsplit.data.model.SplitEntity
import ca.tremblay95.billsplit.domain.model.Split
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class OfflineSplitRepositoryTests {

    lateinit var splitDao : FakeSplitDao
    lateinit var operationDao : FakeOperationDao
    lateinit var operandDao : FakeOperandDao
    lateinit var offlineSplitRepository : OfflineSplitRepository

    @Before
    fun setUp() {
        splitDao = FakeSplitDao()
        operationDao = FakeOperationDao()
        operandDao = FakeOperandDao()

        offlineSplitRepository = OfflineSplitRepository(splitDao, operationDao, operandDao)
    }

    @Test
    fun getAllSplits_emptyList_returnsResultLoading_emptyList_ResultSuccess() = runTest {
        offlineSplitRepository.getAllSplits().test {
            val actualLoading = awaitItem()
            assertThat(actualLoading is Result.Loading).isTrue()

            val actualSuccess = awaitItem()
            if (actualSuccess is Result.Success<List<Split>>) {
                assertThat(actualSuccess.data).isEmpty()
            }
            else {
                fail("Expected Result.Success")
            }

            awaitComplete()
        }
    }

    @Test
    fun getAllSplits_nonEmptyList_returnsResultLoading_completeList_ResultSuccess() = runTest {
        val splitEntities = listOf(
            SplitEntity(1, "1", "1"),
            SplitEntity(2, "2", "2"),
            SplitEntity(3, "3", "3")
        )
        splitEntities.forEach { splitDao.insertSplit(it) }

        offlineSplitRepository.getAllSplits().test {
            val actualLoading = awaitItem()
            assertThat(actualLoading is Result.Loading).isTrue()

            val actualSuccess = awaitItem()
            if (actualSuccess is Result.Success<List<Split>>) {
                assertThat(actualSuccess.data.size).isEqualTo(splitEntities.size)
                
                actualSuccess.data
                    .zip(splitEntities)
                    .forEach { (split, entity) ->
                        assertThat(split).isEqualTo(entity.toSplit())
                    }
            }
            else {
                fail("Expected Result.Success")
            }

            awaitComplete()
        }
    }
}
