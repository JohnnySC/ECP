package com.github.johnnysc.ecp.data.dictionary

import com.github.johnnysc.ecp.data.core.MakeService
import com.github.johnnysc.ecp.data.core.ProvideInterceptor
import com.github.johnnysc.ecp.data.core.ProvideOkHttpClientBuilder
import retrofit2.Retrofit

class NetModule(
    clientBuilder: ProvideOkHttpClientBuilder,
    interceptor: ProvideInterceptor
) : MakeService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            clientBuilder.okHttpClientBuilder()
                .addInterceptor(interceptor.interceptor()).build()
        )
        .build()

    override fun <T> service(clazz: Class<T>): T = retrofit.create(clazz)

    companion object {
        private const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/"
    }
}