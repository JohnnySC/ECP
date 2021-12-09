package com.github.johnnysc.ecp.data.core

import retrofit2.Retrofit

interface ProvideRetrofit {

    fun retrofit(): Retrofit

    class Base(private val baseURL: String, okHttpClientBuilder: ProvideOkHttpClientBuilder) :
        ProvideRetrofit {

        private val client = okHttpClientBuilder.okHttpClientBuilder().build()

        override fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .build()
    }
}