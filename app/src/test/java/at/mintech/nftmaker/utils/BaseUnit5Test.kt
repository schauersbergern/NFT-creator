package at.mintech.nftmaker.utils

import android.content.SharedPreferences
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
open class BaseUnit5Test : KoinTest {

    protected val testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK(relaxed = true)
    protected lateinit var sharedPreferences: SharedPreferences

    @MockK(relaxed = true)
    protected lateinit var editor: SharedPreferences.Editor

    @BeforeEach
    fun baseBefore() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testCoroutineDispatcher)
        setupMockkSharedPreferences(sharedPreferences, editor)
    }


    @AfterEach
    fun baseAfter() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        stopKoin()
        unmockkAll()
    }
}