package com.github.johnnysc.ecp.core

interface Matches<T> {
    fun matches(data: T): Boolean
}