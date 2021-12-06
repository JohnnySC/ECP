package com.github.johnnysc.ecp.data.core

interface ListSerialization {
    fun map(list: List<String>): String

    fun map(data: String): List<String>
}