package ca.tremblay95.billsplit.data.data_source

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ca.tremblay95.billsplit.data.model.SplitEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.test.assertFailsWith

@RunWith(AndroidJUnit4::class)
class SplitDaoTest {

    private lateinit var splitDao : SplitDao
    private lateinit var db : SplitDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            SplitDatabase::class.java
        ).allowMainThreadQueries().build()
        splitDao = db.splitDao()
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        db.close()
    }

    @Test
    fun getSplit_splitReturnedSuccessfully() = runTest {
        val splits = (1..4).map {
            SplitEntity(it, "split$it", "split desc $it")
        }

        splits.forEach { splitDao.insertSplit(it) }

        val result = splitDao.getSplit(splits.last().splitId).first()
        assertThat(result).isEqualTo(splits.last())
    }

    @Test
    fun insertSplitWithUniqueNames_splitInsertedSuccessfully() = runTest {
        val splits = (1..2).map {
            SplitEntity(it, "split$it", "split desc $it")
        }

        splits.forEach { splitDao.insertSplit(it) }

        val result = splitDao.getAllSplits().first()
        assertThat(result).isEqualTo(splits)
    }

    @Test
    fun insertSplitWithDuplicatedNames_throwsSQLiteConstraintError() = runTest {
        val split1 = SplitEntity(1,"split1", "split desc 1")
        val split2 = SplitEntity(2,"split1", "split desc 2")

        splitDao.insertSplit(split1)

        assertFailsWith<SQLiteConstraintException>("Should throw SQLiteConstrainException for non-unique names.") {
            splitDao.insertSplit(split2)
        }
    }

    @Test
    fun deleteSplitSplit_splitSplitDeletedSuccessfully() = runTest {
        val split = SplitEntity(1,"split1", "split desc 1")

        splitDao.insertSplit(split)
        splitDao.deleteSplit(split)

        val result = splitDao.getAllSplits().first()
        assertThat(result).doesNotContain(split)
    }

    @Test
    fun updateSplitSplit_splitSplitUpdatedSuccessfully() = runTest {
        val split = SplitEntity(1,"split1", "split desc 1")
        val updatedSplit = split.copy(name = "new name", description = "new description")

        splitDao.insertSplit(split)
        splitDao.updateSplit(updatedSplit)

        val result = splitDao.getAllSplits().first()
        assertThat(result).contains(updatedSplit)
    }

    @Test
    fun updateSplitSplitNameToExistingName_throwsSQLiteConstraintError() = runTest {
        val split1 = SplitEntity(1,"split1", "split desc 1")
        val split2 = SplitEntity(2,"split2", "split desc 2")
        val updatedSplit1 = split1.copy(name = split2.name)

        splitDao.insertSplit(split1)
        splitDao.insertSplit(split2)

        assertFailsWith<SQLiteConstraintException>("Should throw SQLiteConstrainException for non-unique names.") {
            splitDao.updateSplit(updatedSplit1)
        }
    }
}
