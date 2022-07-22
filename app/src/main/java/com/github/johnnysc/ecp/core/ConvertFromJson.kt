package com.github.johnnysc.ecp.core

import com.github.johnnysc.coremvvm.core.Mapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ConvertFromJson<T> : Mapper<String, T> {

    class Base<T>(private val typeToken: TypeToken<T>, private val gson: Gson) : ConvertFromJson<T> {

        override fun map(data: String): T = gson.fromJson(data, typeToken.type)
    }
}