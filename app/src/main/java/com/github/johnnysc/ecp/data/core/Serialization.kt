package com.github.johnnysc.ecp.data.core

import java.io.Serializable

interface Serialization {
    fun convertToString(data: Serializable)
    fun <T : Serializable> convertFromString(key: String, clazz: Class<T>): T
}