package com.github.johnnysc.ecp.data.core

interface ListSerialization {
    fun string(vararg strings: String): String

    fun listStrings(string: String): List<String>
}