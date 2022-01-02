package com.example.kotlinperformantieapp

import LoggingInterceptor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class NetworkCallViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    constructor():this(Dispatchers.IO)

    private var x = Job()
    private val jobCoScopeVanX = CoroutineScope(x + Dispatchers.IO)

    private var _response: MutableLiveData<String> = MutableLiveData<String>()
    val response: LiveData<String> = _response

    private var _response2: MutableLiveData<String> = MutableLiveData<String>()
    val response2: LiveData<String> = _response2


    fun callRunNetCall(){
        runNetCall("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=")
        runNetCall("https://api.weatherapi.com/v1/current.json?key=")
    }

     private fun runNetCall(url: String) {
         val client = OkHttpClient().newBuilder().followRedirects(false)
                 .followSslRedirects(false).cache(null).addInterceptor(LoggingInterceptor()).build()
         val request = Request.Builder()
                 .url(url).build()
         Thread.sleep(1000)

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (url.contains("openweather")) {
                    _response.postValue(response.body()?.string())
                } else if (url.contains("weatherapi")) {
                    _response2.postValue(response.body()?.string())
                }
            }
        })
    }

    fun callWithCoroutine(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                runNetCall("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=")
            }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                runNetCall("https://api.weatherapi.com/v1/current.json?key=")
            }
        }
    }

    suspend fun runSuspendNetCall(url: String){
        val client = OkHttpClient().newBuilder().followRedirects(false)
                .followSslRedirects(false).cache(null).addInterceptor(LoggingInterceptor()).build()
        val request = Request.Builder()
                .url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (url.contains("openweather")) {
                    _response.postValue(response.body()?.string())
                } else if (url.contains("weatherapi")) {
                    _response2.postValue(response.body()?.string())
                }
            }
        })
    }

    //test data en methoden
    //
    //
    private var isSessionExpired = false

    suspend fun checkSessionExpiry(): Boolean {
        withContext(Dispatchers.IO) {
            delay(5000)
            isSessionExpired = true
        }
        return isSessionExpired
    }

    //data voor niet coroutine test
    private var userDataZonderCo: MutableLiveData<Any> = MutableLiveData<Any>()
    val userDataLiveZonderCo: LiveData<Any> = userDataZonderCo

    //data voor niet coroutine test
    private var userDataZonderCo2: MutableLiveData<Any> = MutableLiveData<Any>()
    val userDataLiveZonderCo2: LiveData<Any> = userDataZonderCo2

    //data voor coroutine test
    private var _userDataLiveMetCo: MutableLiveData<Any> = MutableLiveData<Any>()
    val userDataLive: LiveData<Any> = _userDataLiveMetCo

    fun saveSessionData() {
        runNetCallTest("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=")
    }

    private fun runNetCallTest(url: String) {
        val client = OkHttpClient().newBuilder().followRedirects(false)
                .followSslRedirects(false).addInterceptor(LoggingInterceptor()).build()
        val request = Request.Builder()
                .url(url).cacheControl(CacheControl.FORCE_NETWORK)
                .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                userDataZonderCo.postValue(response.body()?.string())
            }
        })
    }

    fun saveSessionDataSynchroon()  {
        runNetCallTestSynchroon("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=")
        runNetCallTestSynchroon("http://api.weatherapi.com/v1/current.json?key=")
    }

    suspend fun saveSessionLiveData() = coroutineScope {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                runNetCallTestSynchroon("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=")
            }
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                runNetCallTestSynchroon("http://api.weatherapi.com/v1/current.json?key=")
            }
        }
    }

    fun runNetCallTestSynchroon(url: String){

        var deStringRespon : String?= "";
        val client = OkHttpClient().newBuilder().followRedirects(false)
                .followSslRedirects(false).cache(null).addInterceptor(LoggingInterceptor()).build()
        val request = Request.Builder()
                .url(url).build()

        val response: Response = client.newCall(request).execute()
        deStringRespon = response.body()?.string()
        if (url.contains("openweather")) {
            userDataZonderCo.value = deStringRespon
        } else if (url.contains("weatherapi")) {
            userDataZonderCo2.value = deStringRespon
        }
    }


    suspend fun runSuspendNetCallTest(url: String): String{
        var deStringRespon : String?= "";
        val client = OkHttpClient().newBuilder().followRedirects(false)
                .followSslRedirects(false).addInterceptor(LoggingInterceptor()).build()
        val request = Request.Builder()
                .url(url).cacheControl(CacheControl.FORCE_NETWORK)
                .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                deStringRespon = response.body()?.string()
                if (url.contains("openweather")) {
                    userDataZonderCo.postValue(deStringRespon)
                } else if (url.contains("weatherapi")) {
                    userDataZonderCo2.postValue(deStringRespon)
                }
            }
        })
        return deStringRespon.toString()
    }
}

