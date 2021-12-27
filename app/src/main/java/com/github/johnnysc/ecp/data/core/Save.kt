package com.github.johnnysc.ecp.data.core

interface Save<T> {
    fun save(data: T)
}