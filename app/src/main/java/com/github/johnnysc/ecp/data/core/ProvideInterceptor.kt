package com.github.johnnysc.ecp.data.core

import okhttp3.Interceptor

interface ProvideInterceptor {

    fun interceptor(): Interceptor
}