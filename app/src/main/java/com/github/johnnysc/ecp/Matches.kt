package com.github.johnnysc.ecp

interface Matches<T> {
    fun matches(dataToCheck: T): Boolean
}