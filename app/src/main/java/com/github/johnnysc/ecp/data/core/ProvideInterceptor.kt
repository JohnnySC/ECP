package com.github.johnnysc.ecp.data.core

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

interface ProvideInterceptor {

    fun interceptor(): Interceptor
    class Base(private val isDebug: Boolean) {
        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

        fun interceptorLevel(): HttpLoggingInterceptor {
            interceptor.level =
                if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return interceptor
        }
    }

}