package com.github.johnnysc.ecp.data.core

interface MakeService {

    fun <T> service(clazz: Class<T>): T
}