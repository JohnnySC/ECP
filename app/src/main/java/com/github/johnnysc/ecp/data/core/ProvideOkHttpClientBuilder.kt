package com.github.johnnysc.ecp.data.core

import okhttp3.OkHttpClient

interface ProvideOkHttpClientBuilder {
    fun okHttpClientBuilder(): OkHttpClient.Builder
}
