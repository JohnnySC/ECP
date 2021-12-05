package com.github.johnnysc.ecp.core

interface Mapper<S, R> {
    fun map (data: S) : R
}