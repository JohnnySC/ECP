package com.github.johnnysc.ecp.data.core

import retrofit2.Retrofit

interface MakeService {
    fun <T> service(clazz: Class<T>): T
}

class Base(provideRetrofit: ProvideRetrofit) : MakeService {

    private var retrofit : Retrofit

    init {
        retrofit = provideRetrofit.retrofit()
    }

    override fun <T> service(clazz: Class<T>): T = retrofit.create(clazz)

}