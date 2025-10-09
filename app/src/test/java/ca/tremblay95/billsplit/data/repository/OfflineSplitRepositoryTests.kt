package ca.tremblay95.billsplit.data.repository

import app.cash.turbine.test
import ca.tremblay95.billsplit.common.Result
import ca.tremblay95.billsplit.data.fakes.FakeOperandDao
import ca.tremblay95.billsplit.data.fakes.FakeOperationDao
import ca.tremblay95.billsplit.data.fakes.FakeSplitDao
import ca.tremblay95.billsplit.data.mappers.toSplit
import ca.tremblay95.billsplit.data.mappers.toSplitEntity
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
    fun getAllSplits_emptyList_returnsLoading_emptyList_Success() = runTest {
        offlineSplitRepository.getAllSplits().test {
            assertThat(awaitItem() is Result.Loading).isTrue()

            val actual = awaitItem()
            if (actual is Result.Success<List<Split>>) {
                assertThat(actual.data).isEmpty()
            }
            else {
                fail("Expected Result.Success")
            }

            awaitComplete()
        }
    }

    @Test
    fun getAllSplits_nonEmptyList_returnsLoading_completeList_Success() = runTest {
        val splitEntities = listOf(
            SplitEntity(1, "1", "1"),
            SplitEntity(2, "2", "2"),
            SplitEntity(3, "3", "3")
        )
        splitEntities.forEach { splitDao.splitEntities[it.splitId] = it }

        offlineSplitRepository.getAllSplits().test {
            assertThat(awaitItem() is Result.Loading).isTrue()

            val actual = awaitItem()
            if (actual is Result.Success<List<Split>>) {
                assertThat(actual.data.size).isEqualTo(splitEntities.size)
                
                actual.data
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

    @Test
    fun getAllSplits_splitDaoThrowsException_returnsLoading_Error() = runTest {
        splitDao.setShouldThrowException()

        offlineSplitRepository.getAllSplits().test {
            assertThat(awaitItem() is Result.Loading).isTrue()
            assertThat(awaitItem() is Result.Error).isTrue()

            awaitComplete()
        }
    }

    @Test
    fun getSplit_validId_returnsLoading_split_Success() = runTest {
        val splitEntity = SplitEntity(1, "1", "1")
        splitDao.splitEntities[splitEntity.splitId] = splitEntity

        offlineSplitRepository.getSplit(splitEntity.splitId).test {
            assertThat(awaitItem() is Result.Loading).isTrue()

            val actual = awaitItem()
            if (actual is Result.Success<Split>) {
                assertThat(actual.data).isEqualTo(splitEntity.toSplit())
            }
            else {
                fail("Expected Result.Success")
            }

            awaitComplete()
        }
    }

    @Test
    fun getSplit_invalidId_returnsLoading_NotFound() = runTest {
        val splitEntity = SplitEntity(1, "1", "1")
        splitDao.splitEntities[splitEntity.splitId] = splitEntity

        offlineSplitRepository.getSplit(splitEntity.splitId).test {
            assertThat(awaitItem() is Result.Loading).isTrue()

            val actual = awaitItem()
            if (actual is Result.Success<Split>) {
                assertThat(actual.data).isEqualTo(splitEntity.toSplit())
            }
            else {
                fail("Expected Result.Success")
            }

            awaitComplete()
        }
    }

    @Test
    fun getSplit_splitDaoThrowsError_returnsLoading_Error() = runTest {
        splitDao.setShouldThrowException()

        offlineSplitRepository.getSplit(1).test {
            assertThat(awaitItem() is Result.Loading).isTrue()
            assertThat(awaitItem() is Result.Error).isTrue()

            awaitComplete()
        }
    }

    @Test
    fun insertSplit_uniqueSplit_returnsSuccess() = runTest {
        val split = Split(1, "1", "1")

        val actual = offlineSplitRepository.insertSplit(split)
        assertThat(actual is Result.Success).isTrue()
        assertThat(splitDao.splitEntities).containsKey(split.id)
    }

    @Test
    fun insertSplit_duplicateSplit_returnsError() = runTest {
        val split = Split(1, "1", "1")
        splitDao.splitEntities[split.id] = split.toSplitEntity()

        val actual = offlineSplitRepository.insertSplit(split)
        assertThat(actual is Result.Error).isTrue()
    }

    @Test
    fun insertSplit_splitDaoThrowsException_returnsError() = runTest {
        splitDao.setShouldThrowException()

        val actual = offlineSplitRepository.insertSplit(Split(1, "1", "1"))
        assertThat(actual is Result.Error).isTrue()
    }

    @Test
    fun deleteSplit_existingSplit_returnsSuccess() = runTest {
        val split = Split(1, "1", "1")
        splitDao.splitEntities[split.id] = split.toSplitEntity()

        val actual = offlineSplitRepository.deleteSplit(split)
        assertThat(actual is Result.Success).isTrue()
        assertThat(splitDao.splitEntities).doesNotContainKey(split.id)
    }

    @Test
    fun deleteSplit_nonExistentSplit_returnsNotFound() = runTest {
        val actual = offlineSplitRepository.deleteSplit(Split(5, "", ""))
        assertThat(actual is Result.NotFound).isTrue()
    }

    @Test
    fun deleteSplit_splitDaoThrowsException_returnsError() = runTest {
        splitDao.setShouldThrowException()

        val actual = offlineSplitRepository.deleteSplit(Split(1, "", ""))
        assertThat(actual is Result.Error).isTrue()
    }

    @Test
    fun updateSplit_existingSplit_returnsSuccess() = runTest {
        var split = Split(1, "", "")
        splitDao.splitEntities[split.id] = split.toSplitEntity()

        split = split.copy(name = "new name")

        val actual = offlineSplitRepository.updateSplit(split)
        assertThat(actual is Result.Success).isTrue()
        assertThat(splitDao.splitEntities[split.id]).isEqualTo(split.toSplitEntity())
    }

    @Test
    fun updateSplit_nonExistentSplit_returnsNotFound() = runTest {
        val actual = offlineSplitRepository.updateSplit(Split(1, "", ""))
        assertThat(actual is Result.NotFound).isTrue()
    }

    @Test
    fun updateSplit_splitDaoThrowsException_returnsError() = runTest {
        splitDao.setShouldThrowException()

        val actual = offlineSplitRepository.updateSplit(Split(1, "", ""))
        assertThat(actual is Result.Error).isTrue()
    }
}
