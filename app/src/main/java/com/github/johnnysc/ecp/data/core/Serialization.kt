package com.github.johnnysc.ecp.data.core

import com.google.gson.Gson
import java.io.Serializable

interface Serialization {
    fun convertToString(data: Serializable): String
    fun <T : Serializable> convertFromString(key: String, clazz: Class<T>): T

    class Base(private val gson : Gson) : Serialization {

        override fun convertToString(data: Serializable): String = gson.toJson(data)

        override fun <T : Serializable> convertFromString(key: String, clazz: Class<T>): T = gson.fromJson(key, clazz)
    }
}