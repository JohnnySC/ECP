package com.github.johnnysc.ecp.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface Converter {

    fun <T> convert(input: String, typeToken: TypeToken<T>): T

    class Base(private val gson: Gson) : Converter {
        override fun <T> convert(input: String, typeToken: TypeToken<T>): T = gson.fromJson(input, typeToken.type)
    }
}