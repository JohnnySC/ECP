package com.github.johnnysc.ecp.core

interface IntStorage {
    fun save(key: String, value: Int)

    fun read(key: String, defaultValue: Int): Int
}