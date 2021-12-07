package com.github.johnnysc.ecp.data.core

interface MakeService {
    fun <T> service(clazz: Class<T>): T

    class Base(provideRetrofit: ProvideRetrofit) : MakeService {

        private val retrofit = provideRetrofit.retrofit()

        override fun <T> service(clazz: Class<T>): T = retrofit.create(clazz)
    }

    class Test<S>(type: S) : MakeService {

        private val service = type

        override fun <T> service(clazz: Class<T>): T {
            return clazz.cast(service)
        }
    }

}