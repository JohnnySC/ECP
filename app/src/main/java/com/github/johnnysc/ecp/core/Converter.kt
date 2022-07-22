package com.github.johnnysc.ecp.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface Converter<T> {

    fun convert(input: String): T

    class Base<T>(private val typeToken: TypeToken<T>, private val gson: Gson) : Converter<T> {
        override fun convert(input: String): T = gson.fromJson(input, typeToken.type)
    }
}