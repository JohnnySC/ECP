package com.github.johnnysc.ecp.data.core

import retrofit2.Retrofit

interface ProvideRetrofit {

    fun retrofitInstance(): Retrofit

}