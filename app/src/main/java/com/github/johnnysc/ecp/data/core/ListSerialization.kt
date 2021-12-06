package com.github.johnnysc.ecp.data.core

interface ListSerialization {
    fun map(list: List<String>): String

    fun map(data: String): List<String>

    class Base : ListSerialization {
        override fun map(list: List<String>) = list.joinToString(", ")

        override fun map(data: String) = data.split(", ")
    }
}