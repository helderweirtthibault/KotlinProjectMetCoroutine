package com.example.kotlinperformantieapp

import LoggingInterceptor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class CryptoCallViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    constructor():this(Dispatchers.IO)

    var url = "https://api2.binance.com/api/v3/ticker/24hr"


    // The internal MutableLiveData String that stores the most recent response
    private var _response: MutableLiveData<String> = MutableLiveData<String>()
    val response: LiveData<String> = _response


    fun callRunNetCall(){
        runNetCall(url)
    }

    fun callRunNetCallCoroutine(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                runNetCall(url)
            }
        }
    }

    private fun runNetCall(url: String) {
        val client = OkHttpClient().newBuilder().followRedirects(false)
                .followSslRedirects(false).cache(null).addInterceptor(LoggingInterceptor()).build()
        val request = Request.Builder()
                .url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                _response.postValue(response.body()?.string().toString().substring(0, 1500))
            }
        })
    }

    private fun runNetCallSync(url: String) {
        var deStringRespon : String?= "";
        val client = OkHttpClient().newBuilder().cache(null).addInterceptor(LoggingInterceptor()).build()
        val request = Request.Builder()
                .url(url).build()

        val response: Response = client.newCall(request).execute()
        println(response)
        deStringRespon = response.body()?.string()
        println(deStringRespon)
        if (response.isSuccessful()) {
            _response.postValue(response.body()?.string().toString().substring(0, 1500))
        }
    }


    //data voor niet coroutine test
    private var _userDataZonderCo: MutableLiveData<Any> = MutableLiveData<Any>()
    val userDataZonderCo: LiveData<Any> = _userDataZonderCo


    fun saveSessionDataMetCoroutine()  {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                runNetCallTestSynchroon(url)
            }
        }
    }

    fun runNetCallTestSynchroon(url: String){
        var deStringRespon : String?= "";
        val client = OkHttpClient().newBuilder().cache(null).addInterceptor(LoggingInterceptor()).build()
        val request = Request.Builder()
                .url(url).build()

        val response: Response = client.newCall(request).execute()
        println(response)
        deStringRespon = response.body()?.string()
        println(deStringRespon)
        if (response.isSuccessful()) {
            _userDataZonderCo.value = deStringRespon.toString()
        }
    }


}

