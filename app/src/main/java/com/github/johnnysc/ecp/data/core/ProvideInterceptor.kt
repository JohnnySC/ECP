package com.github.johnnysc.ecp.data.core

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

interface ProvideInterceptor {

    fun interceptor(): Interceptor

    class Base(private val isDebug: Boolean) : ProvideInterceptor {
        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

        override fun interceptor():Interceptor {
            interceptor.level =
                    if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return interceptor
        }

    }

}
