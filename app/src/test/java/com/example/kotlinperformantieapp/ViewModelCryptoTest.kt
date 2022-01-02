package com.example.kotlinperformantieapp

import android.widget.Toast
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.logging.Logger
import kotlin.coroutines.ContinuationInterceptor
import kotlin.system.measureTimeMillis


class CryptoViewModelTest{

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val cryptoVM = CryptoCallViewModel(testDispatcher)

    private val log: Logger = Logger.getGlobal()

    @Test
    fun testsSaveSessionDataMetDispatcherZonderCoroutine() = runBlocking {


        val totalExecutionTime = measureTimeMillis {
            cryptoVM.saveSessionDataMetCoroutine()

        }
        delay(4000)
        assertTrue(cryptoVM.userDataZonderCo.value.toString().contains("symbol"))
        print("Total Execution Time: ${totalExecutionTime}")
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


