package com.github.johnnysc.ecp.data.core

import java.io.Serializable

interface Serialization {
    fun <T : Serializable> convertToString(obj: T): String
    fun <T : Serializable> convertFromString(key: String, clazz: Class<T>): T
}