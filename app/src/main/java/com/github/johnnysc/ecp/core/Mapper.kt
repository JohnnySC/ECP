package com.github.johnnysc.ecp.core

interface Mapper<T, K> {
    fun transform (data: T) : K
}