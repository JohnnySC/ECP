package com.github.johnnysc.ecp.data.core

interface ListSerialization {
    fun map(listStrings: List<String>): String

    fun map(string: String): List<String>
}