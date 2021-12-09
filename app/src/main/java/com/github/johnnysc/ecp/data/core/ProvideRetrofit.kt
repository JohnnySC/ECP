package com.github.johnnysc.ecp.data.core

import retrofit2.Retrofit

interface ProvideRetrofit {

    fun retrofit(): Retrofit

    class Base(
        private val baseURL: String,
        private val provideClientBuilder: ProvideOkHttpClientBuilder
    ) : ProvideRetrofit {
        override fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(provideClientBuilder.okHttpClientBuilder().build())
            .build()
    }
}