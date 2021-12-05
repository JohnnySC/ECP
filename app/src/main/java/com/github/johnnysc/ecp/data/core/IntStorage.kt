package com.github.johnnysc.ecp.data.core

interface IntStorage {
    fun save(key: String, value: Int)

    fun read(key: String, defaultValue: Int = 0): Int
}