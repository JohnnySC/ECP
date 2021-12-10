package com.github.johnnysc.ecp.core

import com.github.johnnysc.ecp.data.core.BaseModule
import com.github.johnnysc.ecp.data.core.ProvideInterceptor
import com.github.johnnysc.ecp.data.core.ProvideOkHttpClientBuilder
import com.github.johnnysc.ecp.data.core.ProvideRetrofit
import com.github.johnnysc.ecp.ui.core.MainViewModel
import com.github.johnnysc.ecp.ui.core.NavigationCommunication
import com.google.gson.Gson
import okhttp3.Interceptor
import retrofit2.Retrofit

class CoreModule : BaseModule<MainViewModel> {
    private lateinit var navigationCommunication: NavigationCommunication
    private lateinit var interceptor: Interceptor
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClientBuilder: ProvideOkHttpClientBuilder
    private lateinit var gson: Gson

    fun init(isDebug: Boolean) {
        navigationCommunication = NavigationCommunication.Base()
        interceptor = ProvideInterceptor.Base(isDebug).interceptor()
        okHttpClientBuilder = ProvideOkHttpClientBuilder.Base(interceptor)
        retrofit = ProvideRetrofit.Base(BASE_URL, okHttpClientBuilder).retrofit()
        gson = Gson()
    }

    override fun viewModel() = MainViewModel(NavigationCommunication.Base())

    companion object {
        const val BASE_URL = "some_url" //todo add URL
    }
}