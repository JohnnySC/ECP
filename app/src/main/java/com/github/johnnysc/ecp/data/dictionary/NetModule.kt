package com.github.johnnysc.ecp.data.dictionary

import com.github.johnnysc.ecp.data.core.MakeService
import com.github.johnnysc.ecp.data.core.ProvideInterceptor
import com.github.johnnysc.ecp.data.core.ProvideOkHttpClientBuilder
import com.github.johnnysc.ecp.data.core.ProvideRetrofit

class NetModule(isDebug: Boolean) : MakeService {
    private val retrofit = ProvideRetrofit.Base(
        BASE_URL,
        ProvideOkHttpClientBuilder.Base(ProvideInterceptor.Base(isDebug).interceptor())
    ).retrofit()

    override fun <T> service(clazz: Class<T>): T = retrofit.create(clazz)

    companion object {
        private const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/"
    }
}