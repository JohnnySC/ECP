package com.github.johnnysc.ecp.data.core

import java.io.Serializable

interface Serialization {
    fun <T : Serializable> convertToString(key: String, clazz: Class<T>): String
    fun <T : Serializable> convertToClass(key: String): Class<T>
}