package com.github.johnnysc.ecp

interface SaveString {
    fun write(name: String?, value: String = "")

    fun read(name: String?): String?
}

