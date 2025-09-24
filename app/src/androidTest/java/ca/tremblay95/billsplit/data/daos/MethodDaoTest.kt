package ca.tremblay95.billsplit.data.daos

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ca.tremblay95.billsplit.data.SplitDatabase
import ca.tremblay95.billsplit.data.models.SplitMethod
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.test.assertFailsWith

@RunWith(AndroidJUnit4::class)
class MethodDaoTest {

    private lateinit var methodDao : MethodDao
    private lateinit var db : SplitDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            SplitDatabase::class.java
        ).allowMainThreadQueries().build()
        methodDao = db.methodDao()
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        db.close()
    }

    @Test
    fun getSplitMethod_splitMethodReturnedSuccessfully() = runTest {
        val methods = (1..4).map {
            SplitMethod(it, "method$it", "method desc $it")
        }

        methods.forEach { methodDao.insertSplitMethod(it) }

        val result = methodDao.getSplitMethod(methods.last().methodId).first()
        assertThat(result).isEqualTo(methods.last())
    }

    @Test
    fun insertSplitMethodsWithUniqueNames_splitMethodsInsertedSuccessfully() = runTest {
        val methods = (1..2).map {
            SplitMethod(it, "method$it", "method desc $it")
        }

        methods.forEach { methodDao.insertSplitMethod(it) }

        val result = methodDao.getAllSplitMethods().first()
        assertThat(result).isEqualTo(methods)
    }

    @Test
    fun insertSplitMethodsWithDuplicatedNames_throwsSQLiteConstraintError() = runTest {
        val method1 = SplitMethod(1,"method1", "method desc 1")
        val method2 = SplitMethod(2,"method1", "method desc 2")

        methodDao.insertSplitMethod(method1)

        assertFailsWith<SQLiteConstraintException>("Should throw SQLiteConstrainException for non-unique names.") {
            methodDao.insertSplitMethod(method2)
        }
    }

    @Test
    fun deleteSplitMethod_splitMethodDeletedSuccessfully() = runTest {
        val method = SplitMethod(1,"method1", "method desc 1")

        methodDao.insertSplitMethod(method)
        methodDao.deleteSplitMethod(method)

        val result = methodDao.getAllSplitMethods().first()
        assertThat(result).doesNotContain(method)
    }

    @Test
    fun updateSplitMethod_splitMethodUpdatedSuccessfully() = runTest {
        val method = SplitMethod(1,"method1", "method desc 1")
        val updatedMethod = method.copy(name = "new name", description = "new description")

        methodDao.insertSplitMethod(method)
        methodDao.updateSplitMethod(updatedMethod)

        val result = methodDao.getAllSplitMethods().first()
        assertThat(result).contains(updatedMethod)
    }

    @Test
    fun updateSplitMethodNameToExistingName_throwsSQLiteConstraintError() = runTest {
        val method1 = SplitMethod(1,"method1", "method desc 1")
        val method2 = SplitMethod(2,"method2", "method desc 2")
        val updatedMethod1 = method1.copy(name = method2.name)

        methodDao.insertSplitMethod(method1)
        methodDao.insertSplitMethod(method2)

        assertFailsWith<SQLiteConstraintException>("Should throw SQLiteConstrainException for non-unique names.") {
            methodDao.updateSplitMethod(updatedMethod1)
        }
    }
}
