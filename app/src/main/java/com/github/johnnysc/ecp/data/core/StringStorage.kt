package com.github.johnnysc.ecp.data.core

interface StringStorage {
    fun save(key: String, value: String)

    fun read(key: String, defaultValue: String = ""): String
}

