package com.github.johnnysc.ecp.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface Converter<O> {

    fun convert(input:String):O

    class Base<O>(private val typeToken: TypeToken<O>,private val gson: Gson):Converter<O>
    {
        override fun convert(input: String):O= gson.fromJson(input,typeToken.type)
    }
}