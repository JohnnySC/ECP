package com.github.johnnysc.ecp.data.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvideRetrofitImpl(
    private val okHttpClient: OkHttpClient,
    private val baseUrl: String,
) : ProvideRetrofit {
    override fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}