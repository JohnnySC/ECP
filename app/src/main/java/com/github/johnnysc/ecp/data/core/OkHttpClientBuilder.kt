package com.github.johnnysc.ecp.data.core

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val READ_TIMEOUT = 60L
private const val CONNECT_TIMEOUT = 60L

class OkHttpClientBuilder(
    private val interceptor: Interceptor,
    private val readTimeout: Long = READ_TIMEOUT,
    private val connectTimeout: Long = CONNECT_TIMEOUT
) : ProvideOkHttpClientBuilder {
    override fun okHttpClientBuilder(): OkHttpClient.Builder {
        return this.okHttpClientBuilder()
            .addInterceptor(interceptor)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
    }
}
