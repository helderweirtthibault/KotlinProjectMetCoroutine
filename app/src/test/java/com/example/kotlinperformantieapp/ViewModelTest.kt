package com.example.kotlinperformantieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import kotlin.coroutines.ContinuationInterceptor
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mainViewModel = NetworkCallViewModel(testDispatcher)

    @Test
    fun testsSaveSessionDataMetDispatcher() = runBlocking{
            val totalExecutionTime = measureTimeMillis {
                mainViewModel.saveSessionLiveData()
                delay(5000)
            }
            print("Total Execution Time: ${totalExecutionTime.minus(5000)}")
        assertTrue(mainViewModel.userDataLiveZonderCo.value.toString().contains("coord"))
        assertTrue(mainViewModel.userDataLiveZonderCo2.value.toString().contains("location"))
    }



//Testen van coroutines die op de main thread executen

    @Before
    fun setup() {
        // 1
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // 2
        Dispatchers.resetMain()
        // 3
        testDispatcher.cleanupTestCoroutines()
    }

//    @ExperimentalCoroutinesApi
//    @Test
//    fun testsSaveSessionData() = runBlockingTest {
//        val mainViewModel = NetworkCallViewModel()
//
//        mainViewModel.saveSessionData()
//
//        val userData = mainViewModel.getUserData()
//
//        assertEquals(mainViewModel.callRunNetCall("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=19d9efa32424ab80a516e54af04e0b9d"), userData)
//    }

}


@ExperimentalCoroutinesApi
class MainCoroutineRule : TestWatcher(), TestCoroutineScope by TestCoroutineScope() {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(this.coroutineContext[ContinuationInterceptor] as CoroutineDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }

    override fun apply(base: Statement, description: Description?) = object : Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(testCoroutineDispatcher)

            base.evaluate()

            Dispatchers.resetMain()
            testCoroutineScope.cleanupTestCoroutines()
        }
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutineScope.runBlockingTest {
            block()
        }

}